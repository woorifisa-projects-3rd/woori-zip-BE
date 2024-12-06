package fisa.woorizip.backend.loanchecklist.service;

import fisa.woorizip.backend.loanchecklist.dto.request.LoanChecklistFilterRequest;
import fisa.woorizip.backend.loanchecklist.dto.request.ModifyLoanChecklistRequest;
import fisa.woorizip.backend.loanchecklist.dto.response.ShowLoanChecklistResponse;
import fisa.woorizip.backend.loangoods.dto.response.LoanGoodsResponse;

import java.util.List;

public interface LoanCheckListService {

    List<LoanGoodsResponse> getRecommendLoanGoods(
            Long houseId, LoanChecklistFilterRequest loanGoodsCheckListRequest);

    ShowLoanChecklistResponse getLoanChecklist(Long loanGoodsId);

    void modifyLoanChecklist(
            Long loanChecklistId, ModifyLoanChecklistRequest modifyLoanChecklistRequest);
}
