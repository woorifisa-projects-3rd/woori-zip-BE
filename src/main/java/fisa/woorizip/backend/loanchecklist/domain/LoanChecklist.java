package fisa.woorizip.backend.loanchecklist.domain;

import static fisa.woorizip.backend.loanchecklist.domain.MarriageStatus.NONE_MARRIAGE;
import static fisa.woorizip.backend.loanchecklist.domain.WorkStatus.NONE_WORK_STATUS;
import static fisa.woorizip.backend.loanchecklist.domain.WorkTerm.NONE_TERM;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanChecklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_goods_id", nullable = false)
    private LoanGoods loanGoods;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_status", nullable = false)
    private WorkStatus workStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_term", nullable = false)
    private WorkTerm workTerm;

    @Column(name = "annual_income")
    private Long annualIncome;

    @Column(name = "total_assets")
    private Long totalAssets;

    @Column(name = "contract")
    private Boolean contract;

    @Enumerated(EnumType.STRING)
    @Column(name = "marriage_status", nullable = false)
    private MarriageStatus marriageStatus;

    @Column(name = "lease_deposit")
    private Long leaseDeposit;

    @Column(name = "monthly_rent")
    private Long monthlyRent;

    @Column(name = "exclusive_area")
    private Double exclusiveArea;

    private void updateWorkStatus(WorkStatus workStatus) {
        this.workStatus = isNull(workStatus) ? NONE_WORK_STATUS : workStatus;
    }

    private void updateWorkTerm(WorkTerm workTerm) {
        this.workTerm = isNull(workTerm) ? NONE_TERM : workTerm;
    }

    private void updateAnnualIncome(Long annualIncome) {
        this.annualIncome = annualIncome;
    }

    private void updateTotalAssets(Long totalAssets) {
        this.totalAssets = totalAssets;
    }

    private void updateContract(Boolean contract) {
        this.contract = contract;
    }

    private void updateMarriageStatus(MarriageStatus marriageStatus) {
        this.marriageStatus = isNull(marriageStatus) ? NONE_MARRIAGE : marriageStatus;
    }

    private void updateLeaseDeposit(Long leaseDeposit) {
        this.leaseDeposit = leaseDeposit;
    }

    private void updateMonthlyRent(Long monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    private void updateExclusiveArea(Double exclusiveArea) {
        this.exclusiveArea = exclusiveArea;
    }

    public void updateLoanChecklist(LoanChecklist loanChecklist) {
        updateWorkStatus(loanChecklist.getWorkStatus());
        updateWorkTerm(loanChecklist.getWorkTerm());
        updateAnnualIncome(loanChecklist.getAnnualIncome());
        updateTotalAssets(loanChecklist.getTotalAssets());
        updateContract(loanChecklist.getContract());
        updateMarriageStatus(loanChecklist.getMarriageStatus());
        updateLeaseDeposit(loanChecklist.getLeaseDeposit());
        updateMonthlyRent(loanChecklist.getMonthlyRent());
        updateExclusiveArea(loanChecklist.getExclusiveArea());
    }
}
