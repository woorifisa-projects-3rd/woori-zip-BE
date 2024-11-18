package fisa.woorizip.backend.loanchecklist.dto.response;

import fisa.woorizip.backend.loanchecklist.domain.LoanCheckList;

import lombok.Getter;

@Getter
public class ShowLoanCheckListsResponse {

    private int orderIndex;
    private String content;

    private ShowLoanCheckListsResponse(int orderIndex, String content) {
        this.orderIndex = orderIndex;
        this.content = content;
    }

    public static ShowLoanCheckListsResponse from(LoanCheckList loanCheckList) {
        return new ShowLoanCheckListsResponse(
                loanCheckList.getOrderIndex(), loanCheckList.getContent());
    }
}
