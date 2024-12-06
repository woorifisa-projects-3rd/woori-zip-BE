package fisa.woorizip.backend.loanchecklist.domain;

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
}
