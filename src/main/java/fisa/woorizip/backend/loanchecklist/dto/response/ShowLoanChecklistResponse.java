package fisa.woorizip.backend.loanchecklist.dto.response;

import fisa.woorizip.backend.loanchecklist.domain.LoanChecklist;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ShowLoanChecklistResponse {
    private Long id;
    private Long loanGoodsId;
    private String workStatus;
    private String workTerm;
    private Long annualIncome;
    private Long totalAssets;
    private Boolean contract;
    private String marriageStatus;
    private Long leaseDeposit;
    private Long monthlyRent;
    private Double exclusiveArea;

    public static ShowLoanChecklistResponse from(LoanChecklist loanChecklist) {
        return builder()
                .id(loanChecklist.getId())
                .loanGoodsId(loanChecklist.getLoanGoods().getId())
                .workStatus(loanChecklist.getWorkStatus().getName())
                .workTerm(loanChecklist.getWorkTerm().getName())
                .annualIncome(loanChecklist.getAnnualIncome())
                .totalAssets(loanChecklist.getTotalAssets())
                .contract(loanChecklist.getContract())
                .marriageStatus(loanChecklist.getMarriageStatus().getName())
                .leaseDeposit(loanChecklist.getLeaseDeposit())
                .monthlyRent(loanChecklist.getMonthlyRent())
                .exclusiveArea(loanChecklist.getExclusiveArea())
                .build();
    }
}
