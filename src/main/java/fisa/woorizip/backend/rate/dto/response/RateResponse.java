package fisa.woorizip.backend.rate.dto.response;

import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.domain.RateType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RateResponse {

    private Long id;
    private RateType rateType;
    private String basicRate;
    private String addRate;
    private String normalRate;
    private String specialRate;
    private String minRate;

    public static RateResponse from(Rate rate) {

        return RateResponse.builder()
                .id(rate.getId())
                .rateType(rate.getRateType())
                .basicRate(rate.getBasicRate())
                .addRate(rate.getAddRate())
                .normalRate(rate.getNormalRate())
                .specialRate(rate.getSpecialRate())
                .minRate(rate.getMinRate())
                .build();
    }
}
