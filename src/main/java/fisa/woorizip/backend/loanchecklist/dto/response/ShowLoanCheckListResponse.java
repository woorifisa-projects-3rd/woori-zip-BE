package fisa.woorizip.backend.loanchecklist.dto.response;

import fisa.woorizip.backend.loanchecklist.domain.LoanCheckList;

import lombok.Getter;

@Getter
public class ShowLoanCheckListResponse {

    private int orderIndex;
    private String content;

    private ShowLoanCheckListResponse(int orderIndex, String content) {
        this.orderIndex = orderIndex;
        this.content = content;
    }

    public static ShowLoanCheckListResponse from(LoanCheckList loanCheckList) {
        return new ShowLoanCheckListResponse(
                loanCheckList.getOrderIndex(), loanCheckList.getContent());
    }
}
