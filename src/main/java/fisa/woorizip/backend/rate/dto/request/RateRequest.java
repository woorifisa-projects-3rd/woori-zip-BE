package fisa.woorizip.backend.rate.dto.request;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.domain.RateType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RateRequest {
    private RateType rateType;
    private String basicRate;
    private String addRate;
    private String normalRate;
    private String specialRate;
    private String minRate;

    protected RateRequest() {}

    public Rate toRate(LoanGoods loanGoods) {
        return Rate.builder()
                .loanGoods(loanGoods)
                .rateType(rateType)
                .basicRate(basicRate)
                .addRate(addRate)
                .normalRate(normalRate)
                .specialRate(specialRate)
                .minRate(minRate)
                .build();
    }
}
