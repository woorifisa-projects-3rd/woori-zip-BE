package fisa.woorizip.backend.common;

import static fisa.woorizip.backend.common.exception.errorcode.CommonErrorCode.*;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.common.exception.WooriZipException;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PageArgumentResolver implements PageableArgumentResolver {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final String PAGE = "page";
    private static final String NUMERIC_PATTERN = "-?\\d+";
    private static final int MAX_PAGE_LENGTH = 2;

    @Override
    public Pageable resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        int page = getPage(webRequest);
        if (page < 0) throw new WooriZipException(INVALID_PAGE_SIZE);
        return PageRequest.of(page, getSize(parameter));
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Pageable.class.isAssignableFrom(parameter.getParameterType());
    }

    private int getSize(MethodParameter parameter) {
        PageableDefault pageableDefault = parameter.getParameterAnnotation(PageableDefault.class);
        return isNull(pageableDefault) ? DEFAULT_PAGE_SIZE : pageableDefault.size();
    }

    private int getPage(NativeWebRequest webRequest) {
        String pageParam = webRequest.getParameter(PAGE);
        if (isNull(pageParam)) throw new WooriZipException(PAGE_SIZE_IS_NULL);
        if (!pageParam.matches(NUMERIC_PATTERN)) throw new WooriZipException(NON_NUMERIC_PAGE);
        if (pageParam.length() > MAX_PAGE_LENGTH) throw new WooriZipException(INVALID_PAGE_SIZE);
        return Integer.parseInt(pageParam);
    }
}
