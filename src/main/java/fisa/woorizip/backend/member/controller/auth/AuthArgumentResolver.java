package fisa.woorizip.backend.member.controller.auth;

import static fisa.woorizip.backend.member.AuthErrorCode.NOT_EXIST_ACCESS_TOKEN;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.member.service.auth.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(VerifiedMember.class)
                && MemberIdentity.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public MemberIdentity resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory)
            throws Exception {

        String authorization = webRequest.getHeader(AUTHORIZATION);
        VerifiedMember verifiedMember = parameter.getParameterAnnotation(VerifiedMember.class);
        if (isNull(verifiedMember) || (!verifiedMember.required() && isNull(authorization))) {
            return null;
        }

        validateExistAuthHeader(authorization);
        String accessToken = jwtTokenProvider.extractAccessToken(authorization);
        return jwtTokenProvider.getMemberIdentity(accessToken);
    }

    private void validateExistAuthHeader(String authorization) {
        if (isNull(authorization)) {
            throw new WooriZipException(NOT_EXIST_ACCESS_TOKEN);
        }
    }
}
