package fisa.woorizip.backend.loanchecklist.service;

import fisa.woorizip.backend.loanchecklist.dto.response.ShowLoanCheckListResponse;
import fisa.woorizip.backend.loanchecklist.repository.LoanCheckListRepository;
import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanCheckListServiceImpl implements LoanCheckListService {

    private final LoanCheckListRepository loanCheckListRepository;

    @Override
    public List<ShowLoanCheckListResponse> showLoanCheckLists(LoanGoodsType loanGoodsType) {

        return loanCheckListRepository.findLoanCheckListByLoanGoodsType(loanGoodsType).stream()
                .map(ShowLoanCheckListResponse::from)
                .toList();
    }
}
