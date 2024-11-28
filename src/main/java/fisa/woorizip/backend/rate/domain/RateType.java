package fisa.woorizip.backend.rate.domain;

import lombok.Getter;

@Getter
public enum RateType {
    FIXED("고정금리"),
    NEW_COFIX_SIX_MONTH("신규COFIX기준금리(6개월)"),
    NEW_COFIX_ONE_YEAR("신규COFIX기준금리(1년)"),
    COFIX_SIX_MONTH("신잔액COFIX기준금리(6개월)"),
    COFIX_ONE_YEAR("신잔액COFIX기준금리(1년)");

    private final String name;

    RateType(String name) {
        this.name = name;
    }
}
