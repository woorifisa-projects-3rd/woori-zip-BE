package fisa.woorizip.backend.common;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.log.domain.Log;
import fisa.woorizip.backend.log.repository.LogRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.StringJoiner;
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

    @Pointcut(
            "within(fisa.woorizip.backend..*) && @within(org.springframework.web.bind.annotation.RestController)")
    public void allController() {}

    @Around("allController()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();

        String clientIp = request.getRemoteAddr();
        String requestUrl = createRequestUrl(request);
        String requestBody = createRequestBody(joinPoint);

        ResponseEntity response = (ResponseEntity<?>) joinPoint.proceed();
        String responseStatus = HttpStatus.valueOf(response.getStatusCode().value()).toString();
        Object responseBodyObject = response.getBody();
        String responseBody = isNull(responseBodyObject) ? "" : responseBodyObject.toString();

        MemberIdentity memberIdentity = getMemberIdentity(joinPoint);

        logRepository.save(
                Log.builder()
                        .member(
                                isNull(memberIdentity)
                                        ? null
                                        : findMemberById(memberIdentity.getId()))
                        .clientIp(clientIp)
                        .requestUrl(requestUrl)
                        .requestBody(requestBody)
                        .responseStatus(responseStatus)
                        .responseBody(responseBody)
                        .build());

        return response;
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

    private String createRequestBody(ProceedingJoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs())
                .map(String::valueOf)
                .filter(arg -> arg.startsWith("Sign"))
                .map(arg -> arg.replaceAll(", password=[^,\\)]*", ""))
                .filter(arg -> arg.endsWith(")"))
                .collect(Collectors.joining(", "));
    }

    private MemberIdentity getMemberIdentity(ProceedingJoinPoint joinPoint) {
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
