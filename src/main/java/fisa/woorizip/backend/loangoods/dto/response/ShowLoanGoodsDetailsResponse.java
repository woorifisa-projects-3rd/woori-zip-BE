package fisa.woorizip.backend.loangoods.dto.response;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShowLoanGoodsDetailsResponse {

    private Long id;
    private String name;
    private String description;
    private String content;
    private String imageUrl;
    private LoanGoodsType loanGoodsType;



    public static ShowLoanGoodsDetailsResponse from (LoanGoods loanGoods) {
        return ShowLoanGoodsDetailsResponse.builder()
                .id(loanGoods.getId())
                .name(loanGoods.getName())
                .description(loanGoods.getDescription())
                .content(loanGoods.getContent())
                .imageUrl(loanGoods.getImageUrl())
                .loanGoodsType(loanGoods.getLoanGoodsType())
                .build();
    }

}
