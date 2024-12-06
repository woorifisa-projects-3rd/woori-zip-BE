package fisa.woorizip.backend.rate.dto.request;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.domain.RateType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RateRequest {
    private Long id;
    @NotNull private RateType rateType;
    @NotBlank private String basicRate;
    @NotBlank private String addRate;
    @NotBlank private String normalRate;
    @NotBlank private String specialRate;
    @NotBlank private String minRate;

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

    public Rate toRate() {
        return Rate.builder()
                .id(id)
                .rateType(rateType)
                .basicRate(basicRate)
                .addRate(addRate)
                .normalRate(normalRate)
                .specialRate(specialRate)
                .minRate(minRate)
                .build();
    }
}
