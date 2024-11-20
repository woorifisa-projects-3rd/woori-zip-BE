package fisa.woorizip.backend.loanchecklist.service;

import fisa.woorizip.backend.loanchecklist.dto.response.ShowLoanCheckListResponse;
import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;

import java.util.List;

public interface LoanCheckListService {

    List<ShowLoanCheckListResponse> showLoanCheckLists(LoanGoodsType loanGoodsType);
}
