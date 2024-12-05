package fisa.woorizip.backend.loangoods.dto.response;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@Builder
@ToString
public class ShowLoanGoodsResponse {
    private final boolean hasNext;
    private final List<LoanGoodsResponse> recentlyLoanGoods;

    public static ShowLoanGoodsResponse from(Slice<LoanGoods> loanGoods) {
        return builder()
                .hasNext(loanGoods.hasNext())
                .recentlyLoanGoods(
                        loanGoods.getContent().stream().map(LoanGoodsResponse::from).toList())
                .build();
    }
}
