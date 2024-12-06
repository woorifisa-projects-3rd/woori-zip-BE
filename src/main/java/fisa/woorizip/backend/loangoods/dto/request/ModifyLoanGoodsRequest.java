package fisa.woorizip.backend.loangoods.dto.request;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.domain.LoanType;
import fisa.woorizip.backend.rate.dto.request.RateRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ModifyLoanGoodsRequest {
    @NotBlank private String name;
    @NotNull private LoanType loanType;
    @NotBlank private String target;
    @NotBlank private String limitAmount;
    @NotBlank private String term;
    private String normalRate;
    private String specialRate;
    @NotBlank private String repayType;
    @NotBlank private String guarantee;
    @NotBlank private String targetHouse;
    @NotBlank private String customerCost;
    @NotBlank private String interestMethod;
    private List<RateRequest> rateRequests;

    public ModifyLoanGoodsRequest(
            String name,
            String loanType,
            String target,
            String limitAmount,
            String term,
            String normalRate,
            String specialRate,
            String repayType,
            String guarantee,
            String targetHouse,
            String customerCost,
            String interestMethod,
            List<RateRequest> rateRequests) {
        this.name = name;
        this.loanType = LoanType.from(loanType);
        this.target = target;
        this.limitAmount = limitAmount;
        this.term = term;
        this.normalRate = isNull(normalRate) ? "" : normalRate;
        this.specialRate = isNull(specialRate) ? "" : specialRate;
        this.repayType = repayType;
        this.guarantee = guarantee;
        this.targetHouse = targetHouse;
        this.customerCost = customerCost;
        this.interestMethod = interestMethod;
        this.rateRequests = rateRequests;
    }

    protected ModifyLoanGoodsRequest() {}

    public LoanGoods toLoanGoods() {
        return LoanGoods.builder()
                .name(name)
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
