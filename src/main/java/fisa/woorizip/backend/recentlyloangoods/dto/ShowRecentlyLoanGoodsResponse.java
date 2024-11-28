package fisa.woorizip.backend.recentlyloangoods.dto;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.LoanGoodsResponse;

import lombok.Getter;

import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
public class ShowRecentlyLoanGoodsResponse {
    private final boolean hasNext;
    private final List<LoanGoodsResponse> recentlyLoanGoods;

    private ShowRecentlyLoanGoodsResponse(
            boolean hasNext, List<LoanGoodsResponse> recentlyLoanGoods) {
        this.hasNext = hasNext;
        this.recentlyLoanGoods = recentlyLoanGoods;
    }

    public static ShowRecentlyLoanGoodsResponse from(Slice<LoanGoods> loanGoods) {
        return new ShowRecentlyLoanGoodsResponse(
                loanGoods.hasNext(),
                loanGoods.getContent().stream().map(LoanGoodsResponse::from).toList());
    }
}
