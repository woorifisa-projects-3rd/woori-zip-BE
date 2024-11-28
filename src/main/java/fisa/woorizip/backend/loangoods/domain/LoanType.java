package fisa.woorizip.backend.loangoods.domain;

import lombok.Getter;

@Getter
public enum LoanType {
    LOAN_LEASE_DEPOSIT("전세자금대출"),
    NATIONAL_HOUSING_URBAN_FUND("주택도시기금");

    private final String name;

    LoanType(String name) {
        this.name = name;
    }
}
