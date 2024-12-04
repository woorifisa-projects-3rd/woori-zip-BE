package fisa.woorizip.backend.rate.domain;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_goods_id", nullable = false)
    private LoanGoods loanGoods;

    @Enumerated(EnumType.STRING)
    @Column(name = "rate_type", nullable = false)
    private RateType rateType;

    @Column(name = "basic_rate", nullable = false)
    private String basicRate;

    @Column(name = "add_rate", nullable = false)
    private String addRate;

    @Column(name = "normal_rate", nullable = false)
    private String normalRate;

    @Column(name = "special_rate", nullable = false)
    private String specialRate;

    @Column(name = "min_rate", nullable = false)
    private String minRate;
}
