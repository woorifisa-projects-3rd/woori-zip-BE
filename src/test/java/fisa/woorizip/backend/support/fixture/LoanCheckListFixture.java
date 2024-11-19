package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.loanchecklist.domain.LoanCheckList;
import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;

public class LoanCheckListFixture {

    private long id;
    private int orderIndex = 1;
    private String content = "만34세 이하이십니까?";
    private LoanGoodsType loanGoodsType = LoanGoodsType.JEONSE;

    public static LoanCheckListFixture builder() {
        return new LoanCheckListFixture();
    }

    public LoanCheckListFixture id(Long id) {
        this.id = id;
        return this;
    }

    public LoanCheckListFixture orderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
        return this;
    }

    public LoanCheckListFixture content(String content) {
        this.content = content;
        return this;
    }

    public LoanCheckListFixture loanGoodsType(LoanGoodsType loanGoodsType) {
        this.loanGoodsType = loanGoodsType;
        return this;
    }

    public LoanCheckList build() {
        return LoanCheckList.builder()
                .id(id)
                .orderIndex(orderIndex)
                .content(content)
                .loanGoodsType(loanGoodsType)
                .build();
    }
}
