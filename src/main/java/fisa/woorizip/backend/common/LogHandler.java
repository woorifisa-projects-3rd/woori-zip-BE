package fisa.woorizip.backend.common;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.log.domain.Log;
import fisa.woorizip.backend.log.domain.Log.LogBuilder;
import fisa.woorizip.backend.log.repository.LogRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;
import static java.util.Objects.isNull;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LogHandler {

    private final LogRepository logRepository;
    private final MemberRepository memberRepository;
    private final Map<String, LogBuilder> logs = new HashMap<>();
    private final static String TRACE_ID = "traceId";

    @Pointcut(
            "within(fisa.woorizip.backend..*) && @within(org.springframework.web.bind.annotation.RestController)")
    public void allController() {
    }

    @Before("execution(* fisa.woorizip.backend.member.controller.auth.AuthInterceptor.preHandle(..))")
    public void interceptorRequest(JoinPoint joinPoint) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
        String clientIp = request.getRemoteAddr();
        String requestUrl = createRequestUrl(request);
        String traceId = UUID.randomUUID().toString();
        LogBuilder logBuilder = Log.builder().requestUrl(requestUrl).clientIp(clientIp);
        logs.put(traceId, logBuilder);
        MDC.put(TRACE_ID, traceId);
    }

    @Before("allController()")
    public void controllerRequest(JoinPoint joinPoint) {
        String requestBody = createRequestBody(joinPoint);
        MemberIdentity memberIdentity = getMemberIdentity(joinPoint);
        Member member = isNull(memberIdentity) ? null : findMemberById(memberIdentity.getId());
        String traceId = MDC.get(TRACE_ID);
        logs.getOrDefault(traceId, Log.builder()).requestBody(requestBody).member(member);
    }

    @Around("execution(* fisa.woorizip.backend.common.ApiResponseHandler.beforeBodyWrite(..))")
    public Object logResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletResponse response =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getResponse();

        Object result = joinPoint.proceed();

        if(result instanceof ApiResponse) {
            ApiResponse<?> apiResponse = (ApiResponse<?>) result;
            LogBuilder logBuilder = logs.getOrDefault(MDC.get(TRACE_ID), Log.builder());
            String responseBody = isNull(apiResponse.getData()) ? "" : apiResponse.getData().toString();
            Log log = logBuilder.isSuccess(apiResponse.isSuccess()).responseBody(responseBody).responseStatus(HttpStatus.valueOf(response.getStatus()).toString()).build();
            logRepository.save(log);
        }

        MDC.clear();
        return result;
    }

    private String createRequestUrl(HttpServletRequest request) {
        StringBuilder requestUrl =
                new StringBuilder()
                        .append(request.getMethod())
                        .append(" ")
                        .append(request.getRequestURI());
        String queryString = request.getQueryString();
        if (!isNull(queryString)) requestUrl.append("?").append(queryString);
        return requestUrl.toString();
    }

    private String createRequestBody(JoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs())
                .map(String::valueOf)
                .filter(arg -> arg.endsWith(")"))
                .collect(Collectors.joining(", "));
    }

    private MemberIdentity getMemberIdentity(JoinPoint joinPoint) {
        return (MemberIdentity)
                Arrays.stream(joinPoint.getArgs())
                        .filter(arg -> arg instanceof MemberIdentity)
                        .findAny()
                        .orElse(null);
    }

    private Member findMemberById(Long id) {
        return memberRepository
                .findById(id)
                .orElseThrow(() -> new WooriZipException(MEMBER_NOT_FOUND));
    }
}
