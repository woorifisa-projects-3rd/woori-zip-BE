package fisa.woorizip.backend.support.fixture;

import static fisa.woorizip.backend.rate.domain.RateType.FIXED;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.domain.RateType;

import jakarta.persistence.*;

public class RateFixture {

    private LoanGoods loanGoods;
    private RateType rateType = FIXED;
    private String basic = "3%";
    private String add = "1.5%";
    private String normal = "4.15%";
    private String special = "0%";
    private String min = "4.15%";

    public static RateFixture builder() {
        return new RateFixture();
    }

    public RateFixture loanGoods(LoanGoods loanGoods) {
        this.loanGoods = loanGoods;
        return this;
    }

    public RateFixture rateType(RateType rateType) {
        this.rateType = rateType;
        return this;
    }

    public RateFixture basic(String basic) {
        this.basic = basic;
        return this;
    }

    public RateFixture add(String add) {
        this.add = add;
        return this;
    }

    public RateFixture normal(String normal) {
        this.normal = normal;
        return this;
    }

    public RateFixture special(String special) {
        this.special = special;
        return this;
    }

    public RateFixture min(String min) {
        this.min = min;
        return this;
    }

    public Rate build() {
        return Rate.builder()
                .loanGoods(loanGoods)
                .rateType(rateType)
                .basic(basic)
                .add(add)
                .normal(normal)
                .special(special)
                .min(min)
                .build();
    }
}
