package fisa.woorizip.backend.common;

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

import static fisa.woorizip.backend.common.exception.errorcode.CommonErrorCode.*;
import static java.util.Objects.isNull;

@Component
public class PageArgumentResolver implements PageableArgumentResolver {

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
        return pageableDefault != null ? pageableDefault.size() : 10;
    }

    private int getPage(NativeWebRequest webRequest) {
        String pageParam = webRequest.getParameter("page");
        if (isNull(pageParam)) throw new WooriZipException(PAGE_SIZE_IS_NULL);
        if (!pageParam.matches("-?\\d+")) throw new WooriZipException(NON_NUMERIC_PAGE);
        if (pageParam.length() > 2) throw new WooriZipException(INVALID_PAGE_SIZE);
        return Integer.parseInt(pageParam);
    }
}
