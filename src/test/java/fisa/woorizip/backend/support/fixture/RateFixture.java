package fisa.woorizip.backend.support.fixture;

import static fisa.woorizip.backend.rate.domain.RateType.FIXED;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.domain.RateType;

import jakarta.persistence.*;

public class RateFixture {

    private LoanGoods loanGoods;
    private RateType rateType = FIXED;
    private String basicRate = "3%";
    private String addRate = "1.5%";
    private String normalRate = "4.15%";
    private String specialRate = "0%";
    private String minRate = "4.15%";

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

    public RateFixture basicRate(String basicRate) {
        this.basicRate = basicRate;
        return this;
    }

    public RateFixture addRate(String addRate) {
        this.addRate = addRate;
        return this;
    }

    public RateFixture normalRate(String normalRate) {
        this.normalRate = normalRate;
        return this;
    }

    public RateFixture specialRate(String specialRate) {
        this.specialRate = specialRate;
        return this;
    }

    public RateFixture minRate(String minRate) {
        this.minRate = minRate;
        return this;
    }

    public Rate build() {
        return Rate.builder()
                .loanGoods(loanGoods)
                .rateType(rateType)
                .basicRate(basicRate)
                .addRate(addRate)
                .normalRate(normalRate)
                .specialRate(specialRate)
                .minRate(minRate)
                .build();
    }
}
