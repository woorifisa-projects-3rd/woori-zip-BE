package fisa.woorizip.backend.rate.dto.response;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.LoanGoodsResponse;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.domain.RateType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RateResponse {

    private Long id;
    private LoanGoodsResponse loanGoods;
    private RateType rateType;
    private String basic;
    private String add;
    private String normal;
    private String special;
    private String min;

    public static RateResponse from(Rate rate) {
        LoanGoods loanGoods = rate.getLoanGoods();

        return RateResponse.builder()
                .id(rate.getId())
                .loanGoods(LoanGoodsResponse.from(loanGoods))
                .rateType(rate.getRateType())
                .basic(rate.getBasic())
                .add(rate.getAdd())
                .normal(rate.getNormal())
                .special(rate.getSpecial())
                .min(rate.getMin())
                .build();
    }
}
