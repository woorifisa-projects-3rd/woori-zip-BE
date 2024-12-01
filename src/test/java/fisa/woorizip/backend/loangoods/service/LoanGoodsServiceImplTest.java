package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.dto.response.RateResponse;
import fisa.woorizip.backend.rate.repository.RateRepository;
import fisa.woorizip.backend.support.fixture.LoanGoodsFixture;
import fisa.woorizip.backend.support.fixture.RateFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanGoodsServiceTest {

    @Mock private LoanGoodsRepository loanGoodsRepository;
    @Mock private RateRepository rateRepository;

    @InjectMocks
    private LoanGoodsServiceImpl loanGoodsService;

    @Test
    void 대출상품아이디를_통해_상세정보를_조회한다() {

        LoanGoods loanGoods = LoanGoodsFixture.builder().id(1L).build();
        Rate rate = RateFixture.builder().loanGoods(loanGoods).build();

        when(loanGoodsRepository.findById(loanGoods.getId())).thenReturn(Optional.of(loanGoods));
        when(rateRepository.findByLoanGoodsIdWithFetchJoin(loanGoods)).thenReturn(List.of(rate));

        ShowLoanGoodsDetailResponse response = loanGoodsService.getLoanGoodsDetailsById(loanGoods.getId());

        List<RateResponse> expectedRateList = List.of(RateResponse.from(rate));

        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(response.getName()).isEqualTo(loanGoods.getName()),
                () -> assertThat(response.getImageUrl()).isEqualTo(loanGoods.getImageUrl()),
                () -> assertThat(response.getLoanType()).isEqualTo(loanGoods.getLoanType()),
                () -> assertThat(response.getTarget()).isEqualTo(loanGoods.getTarget()),
                () -> assertThat(response.getLimitAmount()).isEqualTo(loanGoods.getLimitAmount()),
                () -> assertThat(response.getTerm()).isEqualTo(loanGoods.getTerm()),
                () -> assertThat(response.getNormalRate()).isEqualTo(loanGoods.getNormalRate()),
                () -> assertThat(response.getSpecialRate()).isEqualTo(loanGoods.getSpecialRate()),
                () -> assertThat(response.getRepayType()).isEqualTo(loanGoods.getRepayType()),
                () -> assertThat(response.getGuarantee()).isEqualTo(loanGoods.getGuarantee()),
                () -> assertThat(response.getTargetHouse()).isEqualTo(loanGoods.getTargetHouse()),
                () -> assertThat(response.getCustomerCost()).isEqualTo(loanGoods.getCustomerCost()),
                () -> assertThat(response.getInterestMethod()).isEqualTo(loanGoods.getInterestMethod()),
                () -> assertThat(response.getRateList().get(0).getId()).isEqualTo(expectedRateList.get(0).getId()),
                () -> assertThat(response.getRateList().get(0).getRateType()).isEqualTo(expectedRateList.get(0).getRateType()),
                () -> assertThat(response.getRateList().get(0).getNormal()).isEqualTo(expectedRateList.get(0).getNormal()),
                () -> assertThat(response.getRateList().get(0).getSpecial()).isEqualTo(expectedRateList.get(0).getSpecial()),
                () -> assertThat(response.getInterestMethod()).isEqualTo(loanGoods.getInterestMethod()),
                () -> verify(loanGoodsRepository, times(1)).findById(loanGoods.getId()),
                () -> verify(rateRepository, times(1)).findByLoanGoodsIdWithFetchJoin(loanGoods)
        );
    }


}

