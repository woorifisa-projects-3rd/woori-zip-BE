package fisa.woorizip.backend.loangoods.dto.request;

import fisa.woorizip.backend.loangoods.domain.LoanType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ModifyLoanGoodsRequest {
    @NotBlank private String name;
    @NotBlank private LoanType loanType;
    @NotBlank private String target;
    @NotBlank private String limitAmount;
    @NotBlank private String term;
    @NotBlank private String normalRate;
    @NotBlank private String specialRate;
    @NotBlank private String repayType;
    @NotBlank private String guarantee;
    @NotBlank private String targetHouse;
    @NotBlank private String customerCost;
    @NotBlank private String interestMethod;

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
            String interestMethod) {
        this.name = name;
        this.loanType = LoanType.from(loanType);
        this.target = target;
        this.limitAmount = limitAmount;
        this.term = term;
        this.normalRate = normalRate;
        this.specialRate = specialRate;
        this.repayType = repayType;
        this.guarantee = guarantee;
        this.targetHouse = targetHouse;
        this.customerCost = customerCost;
        this.interestMethod = interestMethod;
    }

    protected ModifyLoanGoodsRequest() {}
}
