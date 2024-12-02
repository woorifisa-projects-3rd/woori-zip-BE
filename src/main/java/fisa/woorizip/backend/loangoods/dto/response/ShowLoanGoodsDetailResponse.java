package fisa.woorizip.backend.loangoods.dto.response;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.domain.LoanType;
import fisa.woorizip.backend.rate.dto.response.RateResponse;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ShowLoanGoodsDetailResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private LoanType loanType;
    private String target;
    private String limitAmount;
    private String term;
    private String normalRate;
    private String specialRate;
    private String repayType;
    private String guarantee;
    private String targetHouse;
    private String customerCost;
    private String interestMethod;
    private List<RateResponse> rateList;

    public static ShowLoanGoodsDetailResponse of(LoanGoods loanGoods, List<RateResponse> rateList) {
        return ShowLoanGoodsDetailResponse.builder()
                .id(loanGoods.getId())
                .name(loanGoods.getName())
                .imageUrl(loanGoods.getImageUrl())
                .loanType(loanGoods.getLoanType())
                .target(loanGoods.getTarget())
                .limitAmount(loanGoods.getLimitAmount())
                .term(loanGoods.getTerm())
                .normalRate(loanGoods.getNormalRate())
                .specialRate(loanGoods.getSpecialRate())
                .repayType(loanGoods.getRepayType())
                .guarantee(loanGoods.getGuarantee())
                .targetHouse(loanGoods.getTargetHouse())
                .customerCost(loanGoods.getCustomerCost())
                .interestMethod(loanGoods.getInterestMethod())
                .rateList(rateList)
                .build();
    }
}
