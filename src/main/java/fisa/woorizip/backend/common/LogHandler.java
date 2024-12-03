package fisa.woorizip.backend.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import fisa.woorizip.backend.log.domain.Log;
import fisa.woorizip.backend.log.repository.LogRepository;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LogHandler {

    private final LogRepository logRepository;

    @Pointcut("within(fisa.woorizip.backend..*) && @within(org.springframework.web.bind.annotation.RestController)")
    public void allController() {}


    @Around("allController()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        String queryString = request.getQueryString();
        String args = Arrays.stream(joinPoint.getArgs()).map(String::valueOf).filter(arg -> arg.endsWith(")")).collect(Collectors.joining(", "));
        StringJoiner joiner = new StringJoiner(", ");
        String log = String.format("[%s] [%s?%s] [%s] [%s] ", method, requestURI, queryString, remoteAddr, args);
        logRepository.save(Log.builder().content(log).build());

        return joinPoint.proceed();
    }
}
