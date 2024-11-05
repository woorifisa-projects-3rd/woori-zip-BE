package fisa.woorizip.backend.house.domain;

import lombok.Getter;

@Getter
public enum HousingExpenses {
    MONTHLY_RENT("월세"),
    JEONSE("전세");

    private final String name;

    HousingExpenses(String name) {
        this.name = name;
    }
}
