package fisa.woorizip.backend.loanchecklist.dto.response;

import fisa.woorizip.backend.loanchecklist.domain.LoanCheckList;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ShowLoanCheckListsResponse {

    private int orderIndex;
    private String content;

    private ShowLoanCheckListsResponse(int orderIndex, String content) {
                this.orderIndex = orderIndex;
                this.content = content;
    }

    public static ShowLoanCheckListsResponse from (LoanCheckList loanCheckList) {
        return new ShowLoanCheckListsResponse(
                loanCheckList.getOrderIndex(),
                loanCheckList.getContent()
        );
    }
}
