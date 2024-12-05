package fisa.woorizip.backend.loangoods.domain;

import fisa.woorizip.backend.loangoods.dto.request.ModifyLoanGoodsRequest;
import jakarta.persistence.*;

import lombok.*;

import static java.util.Objects.isNull;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_type", nullable = false)
    private LoanType loanType;

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "limit_amount", nullable = false)
    private String limitAmount;

    @Column(name = "term", nullable = false)
    private String term;

    @Column(name = "normal_rate", nullable = false)
    private String normalRate;

    @Column(name = "special_rate", nullable = false)
    private String specialRate;

    @Column(name = "repay_type", nullable = false)
    private String repayType;

    @Column(name = "guarantee", nullable = false)
    private String guarantee;

    @Column(name = "target_house", nullable = false)
    private String targetHouse;

    @Column(name = "customer_cost", nullable = false)
    private String customerCost;

    @Column(name = "interest_method", nullable = false)
    private String interestMethod;

    private void updateName(String name) {
        if(!isNull(name)) this.name = name;
    }

    private void updateLoanType(LoanType loanType) {
        if(!isNull(loanType)) this.loanType = loanType;
    }

    private void updateTarget(String target) {
        if(!isNull(target)) this.target = target;
    }

    private void updateLimitAmount(String limitAmount) {
        if(!isNull(limitAmount)) this.limitAmount = limitAmount;
    }

    private void updateTerm(String term) {
        if(!isNull(term)) this.term = term;
    }

    private void updateNormalRate(String normalRate) {
        if(!isNull(normalRate)) this.normalRate = normalRate;
    }

    private void updateSpecialRate(String specialRate) {
        if(!isNull(specialRate)) this.specialRate = specialRate;
    }

    private void updateRepayType(String repayType) {
        if(!isNull(repayType)) this.repayType = repayType;
    }

    private void updateGuarantee(String guarantee) {
        if(!isNull(guarantee)) this.guarantee = guarantee;
    }

    private void updateTargetHouse(String targetHouse) {
        if(!isNull(targetHouse)) this.targetHouse = targetHouse;
    }

    private void updateCustomerCost(String customerCost) {
        if(!isNull(customerCost)) this.customerCost = customerCost;
    }

    private void updateInterestMethod(String interestMethod) {
        if(!isNull(interestMethod)) this.interestMethod = interestMethod;
    }

    public void updateLoanGoods(LoanGoods loanGoods) {
        updateName(loanGoods.getName());
        updateLoanType(loanGoods.getLoanType());
        updateTarget(loanGoods.getTarget());
        updateLimitAmount(loanGoods.getLimitAmount());
        updateTerm(loanGoods.getTerm());
        updateNormalRate(loanGoods.getNormalRate());
        updateSpecialRate(loanGoods.getSpecialRate());
        updateRepayType(loanGoods.getRepayType());
        updateGuarantee(loanGoods.getGuarantee());
        updateTargetHouse(loanGoods.getTargetHouse());
        updateCustomerCost(loanGoods.getCustomerCost());
        updateInterestMethod(loanGoods.getInterestMethod());
    }
}
