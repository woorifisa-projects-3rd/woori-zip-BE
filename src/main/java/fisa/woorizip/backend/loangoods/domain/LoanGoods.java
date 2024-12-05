package fisa.woorizip.backend.loangoods.domain;

import fisa.woorizip.backend.loangoods.dto.request.ModifyLoanGoodsRequest;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_type", nullable = false)
    private LoanType loanType;

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "limit_amount", nullable = false)
    private String limitAmount;

    @Column(name = "term", nullable = false)
    private String term;

    @Column(name = "normal_rate", nullable = false)
    private String normalRate;

    @Column(name = "special_rate", nullable = false)
    private String specialRate;

    @Column(name = "repay_type", nullable = false)
    private String repayType;

    @Column(name = "guarantee", nullable = false)
    private String guarantee;

    @Column(name = "target_house", nullable = false)
    private String targetHouse;

    @Column(name = "customer_cost", nullable = false)
    private String customerCost;

    @Column(name = "interest_method", nullable = false)
    private String interestMethod;

    public void updateLoanGoods(ModifyLoanGoodsRequest loanGoodsRequest) {
        this.name = loanGoodsRequest.getName();
        this.loanType = loanGoodsRequest.getLoanType();
        this.target = loanGoodsRequest.getTarget();
        this.limitAmount = loanGoodsRequest.getLimitAmount();
        this.term = loanGoodsRequest.getTerm();
        this.normalRate = loanGoodsRequest.getNormalRate();
        this.specialRate = loanGoodsRequest.getSpecialRate();
        this.repayType = loanGoodsRequest.getRepayType();
        this.guarantee = loanGoodsRequest.getGuarantee();
        this.targetHouse = loanGoodsRequest.getTargetHouse();
        this.customerCost = loanGoodsRequest.getCustomerCost();
        this.interestMethod = loanGoodsRequest.getInterestMethod();
    }
}
