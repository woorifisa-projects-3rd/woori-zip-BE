package fisa.woorizip.backend.loanchecklist.service;

import fisa.woorizip.backend.loanchecklist.dto.response.ShowLoanCheckListsResponse;
import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;

import java.util.List;

public interface LoanCheckListService {

    List<ShowLoanCheckListsResponse> showLoanCheckLists(LoanGoodsType loanGoodsType);
}
