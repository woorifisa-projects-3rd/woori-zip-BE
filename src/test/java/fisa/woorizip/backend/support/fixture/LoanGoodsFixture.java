package fisa.woorizip.backend.support.fixture;

import static fisa.woorizip.backend.loangoods.domain.LoanType.NATIONAL_HOUSING_URBAN_FUND;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.domain.LoanType;

public class LoanGoodsFixture {
    private Long id;
    private String name = "테스트 대출상품";
    private String imageUrl = "테스트 경로";
    private LoanType loanType = NATIONAL_HOUSING_URBAN_FUND;
    private String target = "대출 대상";
    private String limitAmount = "대출 한도";
    private String term = "대출 기간";
    private String normalRate = "기본 금리";
    private String specialRate = "우대 금리";
    private String repayType = "상환 타입";
    private String guarantee = "담보";
    private String targetHouse = "대상 주택";
    private String customerCost = "고객 비용";
    private String interestMethod = "비용";

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
