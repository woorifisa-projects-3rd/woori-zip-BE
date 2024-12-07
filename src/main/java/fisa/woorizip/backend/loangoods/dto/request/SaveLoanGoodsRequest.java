package fisa.woorizip.backend.loangoods.dto.request;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.loanchecklist.dto.request.LoanChecklistRequest;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.domain.LoanType;
import fisa.woorizip.backend.rate.dto.request.RateRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class SaveLoanGoodsRequest {
    @NotBlank private String name;
    @NotBlank private String imageUrl;
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
    @NotNull private LoanChecklistRequest loanChecklistRequest;

    protected SaveLoanGoodsRequest() {}

    public LoanGoods toLoanGoods() {
        return LoanGoods.builder()
                .name(name)
                .imageUrl(imageUrl)
                .loanType(loanType)
                .target(target)
                .limitAmount(limitAmount)
                .term(term)
                .normalRate(isNull(normalRate) ? "" : normalRate)
                .specialRate(isNull(specialRate) ? "" : specialRate)
                .repayType(repayType)
                .guarantee(guarantee)
                .targetHouse(targetHouse)
                .customerCost(customerCost)
                .interestMethod(interestMethod)
                .build();
    }
}
