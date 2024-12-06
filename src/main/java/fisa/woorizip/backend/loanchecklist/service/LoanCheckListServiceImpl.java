package fisa.woorizip.backend.loanchecklist.service;

import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_NOT_FOUND;
import static fisa.woorizip.backend.loanchecklist.LoanChecklistErrorCode.LOAN_CHECKLIST_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.loanchecklist.domain.LoanChecklist;
import fisa.woorizip.backend.loanchecklist.dto.LoanChecklistFilter;
import fisa.woorizip.backend.loanchecklist.dto.request.LoanChecklistFilterRequest;
import fisa.woorizip.backend.loanchecklist.dto.request.ModifyLoanChecklistRequest;
import fisa.woorizip.backend.loanchecklist.dto.response.ShowLoanChecklistResponse;
import fisa.woorizip.backend.loanchecklist.repository.LoanChecklistRepository;
import fisa.woorizip.backend.loangoods.dto.response.LoanGoodsResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanCheckListServiceImpl implements LoanCheckListService {

    private final HouseRepository houseRepository;
    private final LoanChecklistRepository loanCheckListRepository;

    @Override
    @Transactional(readOnly = true)
    public List<LoanGoodsResponse> getRecommendLoanGoods(
            Long houseId, LoanChecklistFilterRequest loanGoodsCheckListRequest) {
        LoanChecklistFilter loanCheckListFilter =
                LoanChecklistFilter.of(findHouseById(houseId), loanGoodsCheckListRequest);
        return loanCheckListRepository.findRecommendLoanGoods(loanCheckListFilter).stream()
                .map(LoanGoodsResponse::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ShowLoanChecklistResponse getLoanChecklist(Long loanGoodsId) {
        return ShowLoanChecklistResponse.from(findLoanChecklistByLoanGoodsId(loanGoodsId));
    }

    @Override
    @Transactional
    public void modifyLoanChecklist(
            Long loanChecklistId, ModifyLoanChecklistRequest modifyLoanChecklistRequest) {
        LoanChecklist loanChecklist = findLoanChecklistById(loanChecklistId);
        loanChecklist.updateLoanChecklist(modifyLoanChecklistRequest.toLoanChecklist());
    }

    private LoanChecklist findLoanChecklistById(Long loanChecklistId) {
        return loanCheckListRepository
                .findById(loanChecklistId)
                .orElseThrow(() -> new WooriZipException(LOAN_CHECKLIST_NOT_FOUND));
    }

    private LoanChecklist findLoanChecklistByLoanGoodsId(Long loanGoodsId) {
        return loanCheckListRepository
                .findByLoanGoodsId(loanGoodsId)
                .orElseThrow(() -> new WooriZipException(LOAN_CHECKLIST_NOT_FOUND));
    }

    private House findHouseById(Long houseId) {
        return houseRepository
                .findById(houseId)
                .orElseThrow(() -> new WooriZipException(HOUSE_NOT_FOUND));
    }
}
