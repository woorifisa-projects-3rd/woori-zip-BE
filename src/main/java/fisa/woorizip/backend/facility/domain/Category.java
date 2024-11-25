package fisa.woorizip.backend.facility.domain;

import static fisa.woorizip.backend.facility.FacilityErrorCode.CATEGORY_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Category {
    BOOK("서적/문구"),
    CAR("자동차정비/유지"),
    CLOTH("의류"),
    CULTURE("문화/취미"),
    FOOD("식당/카페"),
    GROCERY("음식료품"),
    NONE("없음");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public static Category from(String name) {
        return Arrays.stream(Category.values())
                .filter(category -> category.toString().equals(name) || category.name().equals(name))
                .findAny()
                .orElseThrow(() -> new WooriZipException(CATEGORY_NOT_FOUND));
    }
}
