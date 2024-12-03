package fisa.woorizip.backend.loanchecklist.dto.request;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.loanchecklist.domain.WorkStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkTerm;
import fisa.woorizip.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecommendLoanFilter {
    private long deposit;
    private Long monthlyRentFee;
    private Double exclusiveArea;

    public static RecommendLoanFilter of(House house) {
        return builder()
                .deposit(house.getDeposit())
                .monthlyRentFee(house.getMonthlyRentFee())
                .exclusiveArea(house.getExclusiveArea())
                .build();
    }
}
