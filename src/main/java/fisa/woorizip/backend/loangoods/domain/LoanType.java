package fisa.woorizip.backend.loangoods.domain;

import fisa.woorizip.backend.common.exception.WooriZipException;
import lombok.Getter;

import java.util.Arrays;

import static fisa.woorizip.backend.loangoods.LoanGoodsErrorCode.LOAN_TYPE_NOT_FOUND;

@Getter
public enum LoanType {
    LOAN_LEASE_DEPOSIT("전세자금대출"),
    NATIONAL_HOUSING_URBAN_FUND("주택도시기금");

    private final String name;

    LoanType(String name) {
        this.name = name;
    }

    public static LoanType from(String name) {
        return Arrays.stream(LoanType.values())
                .filter(loanType -> loanType.name.equals(name))
                .findAny()
                .orElseThrow(() -> new WooriZipException(LOAN_TYPE_NOT_FOUND));
    }
}
