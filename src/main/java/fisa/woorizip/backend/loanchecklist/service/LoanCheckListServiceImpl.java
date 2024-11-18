package fisa.woorizip.backend.loanchecklist.service;

import fisa.woorizip.backend.loanchecklist.domain.LoanCheckList;
import fisa.woorizip.backend.loanchecklist.dto.response.ShowLoanCheckListsResponse;
import fisa.woorizip.backend.loanchecklist.repository.LoanCheckListRepository;
import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanCheckListServiceImpl implements LoanCheckListService {

    private final LoanCheckListRepository loanCheckListRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ShowLoanCheckListsResponse> showLoanCheckLists(LoanGoodsType loanGoodsType) {
        List<LoanCheckList> loanCheckLists =
                loanCheckListRepository.findLoanCheckListByLoanGoodsType(loanGoodsType);

        return loanCheckLists.stream()
                .map(ShowLoanCheckListsResponse::from)
                .collect(Collectors.toList());
    }
}
