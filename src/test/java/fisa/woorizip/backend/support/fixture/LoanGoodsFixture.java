package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;

public class LoanGoodsFixture {

    private Long id;
    private String name = "대출 상품";
    private String description = "대출 상품 설명";
    private String content = "대출 상품 내용";
    private String imageUrl = "https://test-images/loans";
    private LoanGoodsType loanGoodsType = LoanGoodsType.MONTHLY_RENT;
    private long maxAssets = 345000000L;
    private long maxTotalIncomeLastYear = 50000000L;
    private int maxYearsOfMarriage = 200;
    private int maxAge = 34;
    private int minCreditScore = 600;
    private long minMonthsOfEmployment = 0L;
    private double interestRate = 2.8;

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

    public LoanGoodsFixture description(String description) {
        this.description = description;
        return this;
    }

    public LoanGoodsFixture content(String content) {
        this.content = content;
        return this;
    }

    public LoanGoodsFixture imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public LoanGoodsFixture loanGoodsType(LoanGoodsType loanGoodsType) {
        this.loanGoodsType = loanGoodsType;
        return this;
    }

    public LoanGoodsFixture maxAssets(long maxAssets) {
        this.maxAssets = maxAssets;
        return this;
    }

    public LoanGoodsFixture maxTotalIncomeLastYear(long maxTotalIncomeLastYear) {
        this.maxTotalIncomeLastYear = maxTotalIncomeLastYear;
        return this;
    }

    public LoanGoodsFixture maxYearsOfMarriage(int maxYearsOfMarriage) {
        this.maxYearsOfMarriage = maxYearsOfMarriage;
        return this;
    }

    public LoanGoodsFixture maxAge(int maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    public LoanGoodsFixture minCreditScore(int minCreditScore) {
        this.minCreditScore = minCreditScore;
        return this;
    }

    public LoanGoodsFixture minMonthsOfEmployment(long minMonthsOfEmployment) {
        this.minMonthsOfEmployment = minMonthsOfEmployment;
        return this;
    }

    public LoanGoodsFixture interestRate(double interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public LoanGoods build() {
        return LoanGoods.builder()
                .id(id)
                .name(name)
                .description(description)
                .content(content)
                .imageUrl(imageUrl)
                .loanGoodsType(loanGoodsType)
                .maxAssets(maxAssets)
                .maxTotalIncomeLastYear(maxTotalIncomeLastYear)
                .minCreditScore(minCreditScore)
                .maxAge(maxAge)
                .maxYearsOfMarriage(maxYearsOfMarriage)
                .minMonthsOfEmployment(minMonthsOfEmployment)
                .interestRate(interestRate)
                .build();
    }
}
