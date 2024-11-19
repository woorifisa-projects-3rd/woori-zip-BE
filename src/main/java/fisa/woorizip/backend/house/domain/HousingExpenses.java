package fisa.woorizip.backend.house.domain;

import static fisa.woorizip.backend.house.HouseErrorCode.HOUSING_EXPENSES_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum HousingExpenses {
    MONTHLY_RENT("월세"),
    JEONSE("전세"),
    ANY("모두");

    private final String name;

    HousingExpenses(String name) {
        this.name = name;
    }

    public static HousingExpenses from(String name) {
        return Arrays.stream(HousingExpenses.values())
                .filter(housingExpenses -> housingExpenses.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new WooriZipException(HOUSING_EXPENSES_NOT_FOUND));
    }
}
