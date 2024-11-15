package fisa.woorizip.backend.loangoods.dto.response;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
public class ShowLoanGoodsDetailsResponse {

    private Long id;
    private String name;
    private String description;
    private String content;
    private String imageUrl;
    private LoanGoodsType loanGoodsType;

    private ShowLoanGoodsDetailsResponse(Long id, String name, String description, String content, String imageUrl, LoanGoodsType loanGoodsType) {
                this.id = id;
                this.name = name;
                this.description = description;
                this.content = content;
                this.imageUrl = imageUrl;
                this.loanGoodsType = loanGoodsType;
    }

    public static ShowLoanGoodsDetailsResponse from (LoanGoods loanGoods) {
        return new ShowLoanGoodsDetailsResponse(
                loanGoods.getId(),
                loanGoods.getName(),
                loanGoods.getDescription(),
                loanGoods.getContent(),
                loanGoods.getImageUrl(),
                loanGoods.getLoanGoodsType()
        );
    }

}
