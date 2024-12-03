package fisa.woorizip.backend.loanchecklist.dto.request;

import fisa.woorizip.backend.loanchecklist.domain.MarriageStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkTerm;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RecommendMemberInfoFilter {

    @NotNull private WorkStatus workStatus;
    private WorkTerm workTerm;
    private long annualIncome;
    private long totalAssets;
    @NotNull private MarriageStatus marriageStatus;



    public static RecommendMemberInfoFilter of(WorkStatus workStatus, WorkTerm workTerm,
                                               long annualIncome, long totalAssets,
                                               MarriageStatus marriageStatus) {
        return builder()
                .workStatus(workStatus)
                .workTerm(workTerm)
                .annualIncome(annualIncome)
                .totalAssets(totalAssets)
                .marriageStatus(marriageStatus)
                .build();
    }

}
