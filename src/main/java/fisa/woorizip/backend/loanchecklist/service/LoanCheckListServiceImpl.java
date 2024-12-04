package fisa.woorizip.backend.loanchecklist.service;

import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.loanchecklist.dto.LoanChecklistFilter;
import fisa.woorizip.backend.loanchecklist.dto.request.LoanChecklistRequest;
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
            Long houseId, LoanChecklistRequest loanGoodsCheckListRequest) {
        LoanChecklistFilter loanCheckListFilter =
                LoanChecklistFilter.of(findHouseById(houseId), loanGoodsCheckListRequest);
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
