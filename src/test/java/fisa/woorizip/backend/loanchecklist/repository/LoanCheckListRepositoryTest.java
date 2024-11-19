package fisa.woorizip.backend.loanchecklist.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import fisa.woorizip.backend.loanchecklist.domain.LoanCheckList;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.LoanCheckListFixture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RepositoryTest
class LoanCheckListRepositoryTest {

    @Autowired private LoanCheckListRepository loanCheckListRepository;

    private LoanCheckList 저장(LoanCheckList loanCheckList) {
        return loanCheckListRepository.save(loanCheckList);
    }

    @Test
    void 대출종류에_따라_해당하는_대출체크리스트_조회한다() {

        LoanCheckList loanCheckList = 저장(LoanCheckListFixture.builder().build());

        List<LoanCheckList> findLoanCheckLists =
                loanCheckListRepository.findLoanCheckListByLoanGoodsType(
                        loanCheckList.getLoanGoodsType());

        assertAll(
                () -> {
                    LoanCheckList loanCheckListValue = findLoanCheckLists.get(0);
                    assertThat(loanCheckListValue.getId()).isEqualTo(loanCheckList.getId());
                    assertThat(loanCheckListValue.getOrderIndex())
                            .isEqualTo(loanCheckList.getOrderIndex());
                    assertThat(loanCheckListValue.getContent())
                            .isEqualTo(loanCheckList.getContent());
                    assertThat(loanCheckListValue.getLoanGoodsType())
                            .isEqualTo(loanCheckList.getLoanGoodsType());
                });
    }
}
