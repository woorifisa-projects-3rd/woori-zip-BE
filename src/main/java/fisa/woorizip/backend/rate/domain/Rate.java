package fisa.woorizip.backend.rate.domain;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import jakarta.persistence.*;

import lombok.*;

import static java.util.Objects.isNull;

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

    private void updateRateType(RateType rateType) {
        if(!isNull(rateType)) this.rateType = rateType;
    }

    private void updateBasicRate(String basicRate) {
        if(!isNull(this.basicRate)) this.basicRate = basicRate;
    }

    private void updateAddRate(String addRate) {
        if(!isNull(this.addRate)) this.addRate = addRate;
    }

    private void updateNormalRate(String normalRate) {
        if(!isNull(this.normalRate)) this.normalRate = normalRate;
    }

    private void updateSpecialRate(String specialRate) {
        if(!isNull(this.specialRate)) this.specialRate = specialRate;
    }

    private void updateMinRate(String minRate) {
        if(!isNull(this.minRate)) this.minRate = minRate;
    }

    public void updateRate(Rate rate) {
        updateRateType(rate.getRateType());
        updateBasicRate(rate.getBasicRate());
        updateAddRate(rate.getAddRate());
        updateNormalRate(rate.getNormalRate());
        updateSpecialRate(rate.getSpecialRate());
        updateMinRate(rate.getMinRate());
    }
}
