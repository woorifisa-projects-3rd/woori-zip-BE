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

    @Column(name = "basic", nullable = false)
    private String basic;

    @Column(name = "add", nullable = false)
    private String add;

    @Column(name = "normal", nullable = false)
    private String normal;

    @Column(name = "special", nullable = false)
    private String special;

    @Column(name = "min", nullable = false)
    private String min;
}
