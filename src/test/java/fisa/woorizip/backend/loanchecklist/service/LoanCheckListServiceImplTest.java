package fisa.woorizip.backend.loanchecklist.service;

import fisa.woorizip.backend.loanchecklist.domain.LoanCheckList;
import fisa.woorizip.backend.loanchecklist.dto.response.ShowLoanCheckListsResponse;
import fisa.woorizip.backend.loanchecklist.repository.LoanCheckListRepository;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.loangoods.service.LoanGoodsServiceImpl;
import fisa.woorizip.backend.support.fixture.LoanCheckListFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoanCheckListServiceTest {

    @Mock
    private LoanCheckListRepository loanGoodsRepository;

    @InjectMocks
    private LoanCheckListServiceImpl loanCheckListService;

    @Test
    void 대출체크리스트를_보여준다() {

        LoanCheckList loanCheckList = LoanCheckListFixture.builder().build();

        given(loanGoodsRepository.findLoanCheckListByLoanGoodsType(loanCheckList.getLoanGoodsType())).willReturn(List.of(loanCheckList));

        List<ShowLoanCheckListsResponse> response = loanCheckListService.showLoanCheckLists(loanCheckList.getLoanGoodsType());

        ShowLoanCheckListsResponse loanCheckListResponse = response.get(0);
        assertAll(
                () -> assertThat(loanCheckListResponse.getOrderIndex()).isEqualTo(loanCheckList.getOrderIndex()),
                () -> assertThat(loanCheckListResponse.getContent()).isEqualTo(loanCheckList.getContent()));
    }
}
