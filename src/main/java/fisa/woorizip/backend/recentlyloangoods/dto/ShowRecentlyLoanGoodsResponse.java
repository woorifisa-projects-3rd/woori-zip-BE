package fisa.woorizip.backend.recentlyloangoods.dto;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.LoanGoodsResponse;

import lombok.Builder;
import lombok.Getter;

import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@Builder
public class ShowRecentlyLoanGoodsResponse {
    private final boolean hasNext;
    private final List<LoanGoodsResponse> recentlyLoanGoods;

    public static ShowRecentlyLoanGoodsResponse from(Slice<LoanGoods> loanGoods) {
        return builder()
                .hasNext(loanGoods.hasNext())
                .recentlyLoanGoods(
                        loanGoods.getContent().stream().map(LoanGoodsResponse::from).toList())
                .build();
    }
}
