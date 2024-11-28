package fisa.woorizip.backend.loangoods.dto;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import lombok.Getter;

@Getter
public class LoanGoodsResponse {
    private final Long id;
    private final String loanType;
    private final String name;
    private final String target;
    private final String term;
    private final String limitAmount;
    private final String imageUrl;

    private LoanGoodsResponse(
            Long id,
            String loanType,
            String name,
            String target,
            String term,
            String limitAmount,
            String imageUrl) {
        this.id = id;
        this.loanType = loanType;
        this.name = name;
        this.target = target;
        this.term = term;
        this.limitAmount = limitAmount;
        this.imageUrl = imageUrl;
    }

    public static LoanGoodsResponse from(LoanGoods loanGoods) {
        return new LoanGoodsResponse(
                loanGoods.getId(),
                loanGoods.getLoanType().getName(),
                loanGoods.getName(),
                loanGoods.getTarget(),
                loanGoods.getTerm(),
                loanGoods.getLimitAmount(),
                loanGoods.getImageUrl());
    }
}
