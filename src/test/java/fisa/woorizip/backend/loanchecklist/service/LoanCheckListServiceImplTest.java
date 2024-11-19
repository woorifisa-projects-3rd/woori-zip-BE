package fisa.woorizip.backend.loanchecklist.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import fisa.woorizip.backend.loanchecklist.domain.LoanCheckList;
import fisa.woorizip.backend.loanchecklist.dto.response.ShowLoanCheckListResponse;
import fisa.woorizip.backend.loanchecklist.repository.LoanCheckListRepository;
import fisa.woorizip.backend.support.fixture.LoanCheckListFixture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class LoanCheckListServiceTest {

    @Mock private LoanCheckListRepository loanGoodsRepository;

    @InjectMocks private LoanCheckListServiceImpl loanCheckListService;

    @Test
    void 대출체크리스트를_보여준다() {

        LoanCheckList loanCheckList = LoanCheckListFixture.builder().build();

        given(
                        loanGoodsRepository.findLoanCheckListByLoanGoodsType(
                                loanCheckList.getLoanGoodsType()))
                .willReturn(List.of(loanCheckList));

        List<ShowLoanCheckListResponse> response =
                loanCheckListService.showLoanCheckLists(loanCheckList.getLoanGoodsType());

        ShowLoanCheckListResponse loanCheckListResponse = response.get(0);
        assertAll(
                () ->
                        assertThat(loanCheckListResponse.getOrderIndex())
                                .isEqualTo(loanCheckList.getOrderIndex()),
                () ->
                        assertThat(loanCheckListResponse.getContent())
                                .isEqualTo(loanCheckList.getContent()));
    }
}
