package fisa.woorizip.backend.facility.domain;

import fisa.woorizip.backend.common.exception.WooriZipException;
import lombok.Getter;

import java.util.Arrays;

import static fisa.woorizip.backend.facility.FacilityErrorCode.CATEGORY_NOT_FOUND;

@Getter
public enum Category {
    FOOD("요식업"),
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
