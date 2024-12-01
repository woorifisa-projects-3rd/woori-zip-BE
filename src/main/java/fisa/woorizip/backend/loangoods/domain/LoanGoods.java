package fisa.woorizip.backend.loangoods.domain;

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


}
