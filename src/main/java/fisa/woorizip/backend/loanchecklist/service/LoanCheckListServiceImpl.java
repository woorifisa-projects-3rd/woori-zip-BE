package fisa.woorizip.backend.loanchecklist.service;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.loanchecklist.dto.LoanCheckListFilter;
import fisa.woorizip.backend.loanchecklist.dto.request.LoanCheckListRequest;
import fisa.woorizip.backend.loanchecklist.repository.LoanChecklistRepository;
import fisa.woorizip.backend.loangoods.dto.response.LoanGoodsResponse;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LoanCheckListServiceImpl implements LoanCheckListService {

    private final HouseRepository houseRepository;
    private final LoanChecklistRepository loanCheckListRepository;

    @Override
    @Transactional(readOnly = true)
    public List<LoanGoodsResponse> getRecommendLoanGoods(
            Long houseId, LoanCheckListRequest loanGoodsCheckListRequest) {
        LoanCheckListFilter loanCheckListFilter =
                LoanCheckListFilter.of(findHouseById(houseId), loanGoodsCheckListRequest);
        return loanCheckListRepository.findRecommendLoanGoods(loanCheckListFilter).stream()
                .map(LoanGoodsResponse::from)
                .toList();
    }

    private House findHouseById(Long houseId) {
        return houseRepository
                .findById(houseId)
                .orElseThrow(() -> new WooriZipException(HOUSE_NOT_FOUND));
    }
}
