package fisa.woorizip.backend.member.controller.auth;

import static fisa.woorizip.backend.member.AuthErrorCode.AUTHORIZATION_HEADER_NOT_FOUND;
import static fisa.woorizip.backend.member.AuthErrorCode.INSUFFICIENT_PERMISSIONS;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.service.auth.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Login login = handlerMethod.getMethodAnnotation(Login.class);
        if (isNull(login)) return true;

        String authorization = request.getHeader(AUTHORIZATION);
        validateExistAuthHeader(authorization);

        String accessToken = jwtTokenProvider.extractAccessToken(authorization);
        jwtTokenProvider.validToken(accessToken);
        validateInsufficientRole(jwtTokenProvider.getMemberRole(accessToken), login.role());
        return true;
    }

    private void validateInsufficientRole(Role memberRole, Role[] requiredRoles) {
        Arrays.stream(requiredRoles)
                .filter(requiredRole -> memberRole == requiredRole)
                .findAny()
                .orElseThrow(() -> new WooriZipException(INSUFFICIENT_PERMISSIONS));
    }

    private void validateExistAuthHeader(String authorization) {
        if (isNull(authorization)) {
            throw new WooriZipException(AUTHORIZATION_HEADER_NOT_FOUND);
        }
    }
}
