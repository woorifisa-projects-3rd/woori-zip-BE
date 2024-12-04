package fisa.woorizip.backend.support.fixture;

import static fisa.woorizip.backend.loangoods.domain.LoanType.LOAN_LEASE_DEPOSIT;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.domain.LoanType;

import jakarta.persistence.*;

public class LoanGoodsFixture {
    private Long id;
    private String name = "우리 월세대출";
    private String imageUrl = "image/url";
    private LoanType loanType = LOAN_LEASE_DEPOSIT;
    private String target = "부부합산 자산기준 * 3.45억원 이하인 고객";
    private String limitAmount = "월세 한도 내에서 매월 최대 60만원씩 2년간 총 1,440만원";
    private String term = "2년";
    private String normalRate = "3%";
    private String specialRate = "1%";
    private String repayType = "일시납";
    private String guarantee = "보장";
    private String targetHouse = "지방";
    private String customerCost = "5천";
    private String interestMethod = "세부";

    public static LoanGoodsFixture builder() {
        return new LoanGoodsFixture();
    }

    public LoanGoodsFixture id(Long id) {
        this.id = id;
        return this;
    }

    public LoanGoodsFixture name(String name) {
        this.name = name;
        return this;
    }

    public LoanGoodsFixture imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public LoanGoodsFixture loanType(LoanType loanType) {
        this.loanType = loanType;
        return this;
    }

    public LoanGoodsFixture target(String target) {
        this.target = target;
        return this;
    }

    public LoanGoodsFixture limitAmount(String limitAmount) {
        this.limitAmount = limitAmount;
        return this;
    }

    public LoanGoodsFixture term(String term) {
        this.term = term;
        return this;
    }

    public LoanGoodsFixture normalRate(String normalRate) {
        this.normalRate = normalRate;
        return this;
    }

    public LoanGoodsFixture specialRate(String specialRate) {
        this.specialRate = specialRate;
        return this;
    }

    public LoanGoodsFixture repayType(String repayType) {
        this.repayType = repayType;
        return this;
    }

    public LoanGoodsFixture guarantee(String guarantee) {
        this.guarantee = guarantee;
        return this;
    }

    public LoanGoodsFixture targetHouse(String targetHouse) {
        this.targetHouse = targetHouse;
        return this;
    }

    public LoanGoodsFixture customerCost(String customerCost) {
        this.customerCost = customerCost;
        return this;
    }

    public LoanGoodsFixture interestMethod(String interestMethod) {
        this.interestMethod = interestMethod;
        return this;
    }

    public LoanGoods build() {
        return LoanGoods.builder()
                .id(id)
                .name(name)
                .imageUrl(imageUrl)
                .loanType(loanType)
                .target(target)
                .limitAmount(limitAmount)
                .term(term)
                .normalRate(normalRate)
                .specialRate(specialRate)
                .repayType(repayType)
                .guarantee(guarantee)
                .targetHouse(targetHouse)
                .customerCost(customerCost)
                .interestMethod(interestMethod)
                .build();
    }
}
