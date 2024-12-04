package fisa.woorizip.backend.loanchecklist.dto;

import fisa.woorizip.backend.house.domain.House;

import fisa.woorizip.backend.loanchecklist.domain.MarriageStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkTerm;
import fisa.woorizip.backend.loanchecklist.dto.request.LoanCheckListRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoanCheckListFilter {
    private WorkStatus workStatus;
    private WorkTerm workTerm;
    private Long annualIncome;
    private Long totalAssets;
    private Boolean contract;
    private MarriageStatus marriageStatus;
    private long leaseDeposit;
    private Long monthlyRent;
    private double exclusiveArea;

    public static LoanCheckListFilter of(House house, LoanCheckListRequest request) {
        return builder()
                .workStatus(request.getWorkStatus())
                .workTerm(request.getWorkTerm())
                .annualIncome(request.getAnnualIncome())
                .totalAssets(request.getTotalAssets())
                .contract(request.getContract())
                .marriageStatus(request.getMarriageStatus())
                .leaseDeposit(house.getDeposit())
                .monthlyRent(house.getMonthlyRentFee())
                .exclusiveArea(house.getExclusiveArea())
                .build();
    }
}