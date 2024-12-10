package fisa.woorizip.backend.common;

import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.log.domain.Log;
import fisa.woorizip.backend.log.domain.Log.LogBuilder;
import fisa.woorizip.backend.log.repository.LogRepository;
import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.member.service.auth.JwtTokenProvider;

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
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LogHandler {

    private final LogRepository logRepository;
    private final MemberRepository memberRepository;
    private final Map<String, LogBuilder> logs = new HashMap<>();
    private static final String TRACE_ID = "traceId";
    private final JwtTokenProvider jwtTokenProvider;

    @Pointcut(
            "within(fisa.woorizip.backend..*) &&"
                    + " @within(org.springframework.web.bind.annotation.RestController)")
    public void allController() {}

    @Pointcut("execution(*" + " fisa.woorizip.backend.common.LogFilter.doFilter(..))")
    public void logFilter() {}

    @Pointcut("execution(* fisa.woorizip.backend.common.ApiResponseHandler.beforeBodyWrite(..))")
    public void apiResponseHandler() {}

    @Before("logFilter()")
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
        String traceId = MDC.get(TRACE_ID);
        logs.getOrDefault(traceId, Log.builder())
                .requestBody(requestBody)
                .member(getMember(joinPoint));
    }

    @Around("apiResponseHandler()")
    public Object logResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletResponse response =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getResponse();

        Object result = joinPoint.proceed();

        if (result instanceof ApiResponse) {
            ApiResponse<?> apiResponse = (ApiResponse<?>) result;
            LogBuilder logBuilder = logs.getOrDefault(MDC.get(TRACE_ID), Log.builder());
            String responseBody =
                    isNull(apiResponse.getData()) ? "" : apiResponse.getData().toString();
            Log log =
                    logRepository.save(
                            logBuilder
                                    .isSuccess(apiResponse.isSuccess())
                                    .responseBody(responseBody)
                                    .responseStatus(
                                            HttpStatus.valueOf(response.getStatus()).toString())
                                    .build());
            printLog(log);
        }
        MDC.clear();
        return result;
    }

    private Member getMember(JoinPoint joinPoint) {
        MemberIdentity memberIdentity = getMemberIdentity(joinPoint);
        if (!isNull(memberIdentity)) {
            return findMemberById(memberIdentity.getId());
        }

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        if (method.isAnnotationPresent(Login.class)) {
            Long memberId = getMemberIdFromRequestHeader();
            return findMemberById(memberId);
        }

        return null;
    }

    private Long getMemberIdFromRequestHeader() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String accessToken = jwtTokenProvider.extractAccessToken(authorizationHeader);
        Long memberId = jwtTokenProvider.getMemberId(accessToken);
        return memberId;
    }

    private void printLog(Log logging) {
        log.info(
                "\n"
                    + " [Success: {} | Log ID: {}] \n"
                    + " Member: {} | \n"
                    + " IP: {} | \n"
                    + " URL: {} | \n"
                    + " ReqBody: {} | \n"
                    + " RespStatus: {} | \n"
                    + " RespBody: {} | \n"
                    + " CreatedAt: {}\n",
                logging.isSuccess(),
                logging.getId(),
                logging.getMember() != null ? logging.getMember().getId() : "Anonymous",
                logging.getClientIp(),
                logging.getRequestUrl(),
                logging.getRequestBody(),
                logging.getResponseStatus(),
                logging.getResponseBody(),
                logging.getCreatedAt());
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
                .filter(arg -> arg.startsWith("Sign"))
                .map(arg -> arg.replaceAll(", password=[^,\\)]*", ""))
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
