package fisa.woorizip.backend.loangoods.domain;

import lombok.Getter;

@Getter
public enum LoanGoodsType {

    MONTHLY_RENT("월세"), JEONSE("전세");

    private final String name;

    LoanGoodsType(String name) {
        this.name = name;
    }
}
