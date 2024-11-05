package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;

public class LoanGoodsFixture {

    private Long id;
    private String name = "대출 상품";
    private String description = "대출 상품 설명";
    private String content = "대출 상품 내용";
    private String imageUrl = "https://test-images/loans";
    private LoanGoodsType loanGoodsType = LoanGoodsType.MONTHLY_RENT;

    public static LoanGoodsFixture builder() {
        return new LoanGoodsFixture();
    }

    public LoanGoodsFixture id(Long id) {
        this.id = id;
        return this;
    }

    public LoanGoodsFixture name(String name) {
        this.name = name;
        return this;
    }

    public LoanGoodsFixture description(String description) {
        this.description = description;
        return this;
    }

    public LoanGoodsFixture content(String content) {
        this.content = content;
        return this;
    }

    public LoanGoodsFixture imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public LoanGoodsFixture loanGoodsType(LoanGoodsType loanGoodsType) {
        this.loanGoodsType = loanGoodsType;
        return this;
    }

    public LoanGoods build() {
        return LoanGoods.builder()
                .id(id)
                .name(name)
                .description(description)
                .content(content)
                .imageUrl(imageUrl)
                .loanGoodsType(loanGoodsType)
                .build();
    }

}
