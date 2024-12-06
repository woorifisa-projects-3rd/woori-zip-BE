package fisa.woorizip.backend.loanchecklist.dto.request;

import fisa.woorizip.backend.loanchecklist.domain.LoanChecklist;
import fisa.woorizip.backend.loanchecklist.domain.MarriageStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkTerm;
import lombok.Getter;
import lombok.ToString;

import static fisa.woorizip.backend.loanchecklist.domain.MarriageStatus.NONE_MARRIAGE;
import static fisa.woorizip.backend.loanchecklist.domain.WorkStatus.NONE_WORK_STATUS;
import static fisa.woorizip.backend.loanchecklist.domain.WorkTerm.NONE_TERM;
import static java.util.Objects.isNull;

@Getter
@ToString
public class ModifyLoanChecklistRequest {
    private WorkStatus workStatus;
    private WorkTerm workTerm;
    private Long annualIncome;
    private Long totalAssets;
    private Boolean contract;
    private MarriageStatus marriageStatus;
    private Long leaseDeposit;
    private Long monthlyRent;
    private Double exclusiveArea;

    public ModifyLoanChecklistRequest(
            String workStatus,
            String workTerm,
            Long annualIncome,
            Long totalAssets,
            Boolean contract,
            String marriageStatus,
            Long leaseDeposit,
            Long monthlyRent,
            Double exclusiveArea) {
        this.workStatus = isNull(workStatus) ? NONE_WORK_STATUS : WorkStatus.from(workStatus);
        this.workTerm = isNull(workTerm) ? NONE_TERM : WorkTerm.from(workTerm);
        this.annualIncome = annualIncome;
        this.totalAssets = totalAssets;
        this.contract = contract;
        this.marriageStatus = isNull(marriageStatus) ? NONE_MARRIAGE : MarriageStatus.from(marriageStatus);
        this.leaseDeposit = leaseDeposit;
        this.monthlyRent = monthlyRent;
        this.exclusiveArea = exclusiveArea;
    }

    protected ModifyLoanChecklistRequest() {}

    public LoanChecklist toLoanChecklist() {
        return LoanChecklist.builder()
                .workStatus(workStatus)
                .workTerm(workTerm)
                .annualIncome(annualIncome)
                .totalAssets(totalAssets)
                .contract(contract)
                .marriageStatus(marriageStatus)
                .leaseDeposit(leaseDeposit)
                .monthlyRent(monthlyRent)
                .exclusiveArea(exclusiveArea)
                .build();
    }
}
