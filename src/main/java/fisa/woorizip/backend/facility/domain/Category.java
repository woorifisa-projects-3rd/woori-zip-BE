package fisa.woorizip.backend.facility.domain;

import static fisa.woorizip.backend.facility.FacilityErrorCode.CATEGORY_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Category {
    FOOD("요식업"),
    TEST("테스트용 카테고리"),
    NONE("없음");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public static Category from(String name) {
        return Arrays.stream(Category.values())
                .filter(category -> category.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new WooriZipException(CATEGORY_NOT_FOUND));
    }
}
