package fisa.woorizip.backend.loangoods.dto;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoanGoodsResponse {
    private final Long id;
    private final String loanType;
    private final String name;
    private final String target;
    private final String term;
    private final String limitAmount;
    private final String imageUrl;

    public static LoanGoodsResponse from(LoanGoods loanGoods) {
        return builder()
                .id(loanGoods.getId())
                .loanType(loanGoods.getLoanType().getName())
                .name(loanGoods.getName())
                .target(loanGoods.getTarget())
                .term(loanGoods.getTerm())
                .limitAmount(loanGoods.getLimitAmount())
                .imageUrl(loanGoods.getImageUrl())
                .build();
    }
}
