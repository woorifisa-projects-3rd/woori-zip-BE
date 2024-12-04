package fisa.woorizip.backend.loanchecklist.dto.request;

import fisa.woorizip.backend.loanchecklist.domain.MarriageStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkTerm;

import lombok.*;

import java.beans.ConstructorProperties;

@Getter
public class LoanChecklistRequest {

    private Long annualIncome;
    private Long totalAssets;
    private MarriageStatus marriageStatus;
    private Boolean contract;
    private WorkStatus workStatus;
    private WorkTerm workTerm;

    @ConstructorProperties({
            "annualIncome",
            "totalAssets",
            "marriageStatus",
            "contract",
            "workStatus",
            "workTerm"
    })
    private LoanChecklistRequest(
            Long annualIncome,
            Long totalAssets,
            String marriageStatus,
            Boolean contract,
            String workStatus,
            String workTerm
    ) {
        this.annualIncome = annualIncome;
        this.totalAssets = totalAssets;
        this.marriageStatus = MarriageStatus.from(marriageStatus);
        this.contract = contract;
        this.workStatus = WorkStatus.from(workStatus);
        this.workTerm = WorkTerm.from(workTerm);
    }
}
