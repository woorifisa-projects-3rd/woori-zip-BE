package fisa.woorizip.backend.loanchecklist.dto.request;

import fisa.woorizip.backend.loanchecklist.domain.LoanChecklist;
import fisa.woorizip.backend.loanchecklist.domain.MarriageStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkTerm;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static fisa.woorizip.backend.loanchecklist.domain.MarriageStatus.NONE_MARRIAGE;
import static fisa.woorizip.backend.loanchecklist.domain.WorkStatus.NONE_WORK_STATUS;
import static fisa.woorizip.backend.loanchecklist.domain.WorkTerm.NONE_TERM;
import static java.util.Objects.isNull;

@Getter
@AllArgsConstructor
public class LoanChecklistRequest {
    private WorkStatus workStatus;
    private WorkTerm workTerm;
    private Long annualIncome;
    private Long totalAssets;
    private Boolean contract;
    private MarriageStatus marriageStatus;
    private Long leaseDeposit;
    private Long monthlyRent;
    private Double exclusiveArea;

    protected LoanChecklistRequest() {}

    public LoanChecklist toLoanChecklist(LoanGoods loanGoods) {
        return LoanChecklist.builder()
                .loanGoods(loanGoods)
                .workStatus(isNull(workStatus) ? NONE_WORK_STATUS : workStatus)
                .workTerm(isNull(workTerm) ? NONE_TERM : workTerm)
                .annualIncome(annualIncome)
                .totalAssets(totalAssets)
                .contract(contract)
                .marriageStatus(isNull(marriageStatus) ? NONE_MARRIAGE : marriageStatus)
                .leaseDeposit(leaseDeposit)
                .monthlyRent(monthlyRent)
                .exclusiveArea(exclusiveArea)
                .build();
    }
}
