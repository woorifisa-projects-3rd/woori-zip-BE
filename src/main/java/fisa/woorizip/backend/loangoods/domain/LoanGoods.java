package fisa.woorizip.backend.loangoods.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "loan_goods_type", nullable = false)
    private LoanGoodsType loanGoodsType;

    @Column(name = "max_assets", nullable = false)
    private Long maxAssets;

    @Column(name = "max_total_income_last_year", nullable = false)
    private Long maxTotalIncomeLastYear;

    @Column(name = "max_years_of_marriage", nullable = false)
    private Integer maxYearsOfMarriage;

    @Column(name = "max_age", nullable = false)
    private Integer maxAge;

    @Column(name = "min_credit_score", nullable = false)
    private Integer minCreditScore;

    @Column(name = "min_months_of_employment", nullable = false)
    private Long minMonthsOfEmployment;

    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;


}
