package fisa.woorizip.backend.loanchecklist.dto;

import fisa.woorizip.backend.house.domain.House;

import fisa.woorizip.backend.loanchecklist.domain.MarriageStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkTerm;
import fisa.woorizip.backend.loanchecklist.dto.request.LoanGoodsCheckListRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoanGoodsCheckListFilter {
    private WorkStatus workStatus;
    private WorkTerm workTerm;
    private Long annualIncome;
    private Long totalAssets;
    private Boolean contract;
    private MarriageStatus marriageStatus;
    private long leaseDeposit;
    private Long monthlyRentFee;
    private double exclusiveArea;

    public static LoanGoodsCheckListFilter of(House house, LoanGoodsCheckListRequest request) {
        return builder()
                .workStatus(request.getWorkStatus())
                .workTerm(request.getWorkTerm())
                .annualIncome(request.getAnnualIncome())
                .totalAssets(request.getTotalAssets())
                .contract(request.getContract())
                .marriageStatus(request.getMarriageStatus())
                .leaseDeposit(house.getDeposit())
                .monthlyRentFee(house.getMonthlyRentFee())
                .exclusiveArea(house.getExclusiveArea())
                .build();
    }
}
