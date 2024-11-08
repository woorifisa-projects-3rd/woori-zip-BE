package fisa.woorizip.backend.member.controller.auth;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.service.auth.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import static fisa.woorizip.backend.member.AuthErrorCode.INSUFFICIENT_PERMISSIONS;
import static fisa.woorizip.backend.member.AuthErrorCode.NOT_EXIST_ACCESS_TOKEN;
import static java.util.Objects.isNull;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Login login = handlerMethod.getMethodAnnotation(Login.class);
        if (isNull(login)) return true;

        String authorization = request.getHeader(AUTHORIZATION);
        validateExistAccessToken(authorization);

        String accessToken = jwtTokenProvider.extractAccessToken(authorization);
        jwtTokenProvider.validToken(accessToken);
        validateInfufficientRole(jwtTokenProvider.getMemberRole(accessToken), login.role());

        return true;
    }

    private void validateInfufficientRole(Role memberRole, Role requiredRole) {
        if(!memberRole.canAccess(requiredRole)) {
            throw new WooriZipException(INSUFFICIENT_PERMISSIONS);
        }
    }

    private void validateExistAccessToken(String authorization) {
        if (isNull(authorization)) {
            throw new WooriZipException(NOT_EXIST_ACCESS_TOKEN);
        }
    }
}
