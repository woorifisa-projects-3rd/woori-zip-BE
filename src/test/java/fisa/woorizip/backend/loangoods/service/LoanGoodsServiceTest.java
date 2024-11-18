package fisa.woorizip.backend.loangoods.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailsResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.support.fixture.LoanGoodsFixture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LoanGoodsServiceTest {

    @Mock private LoanGoodsRepository loanGoodsRepository;

    @InjectMocks private LoanGoodsServiceImpl loanGoodsService;

    @Test
    void 대출상품_상세정보를_반환한다() {
        LoanGoods loanGoods = LoanGoodsFixture.builder().build();

        given(loanGoodsRepository.findById(loanGoods.getId())).willReturn(Optional.of(loanGoods));

        ShowLoanGoodsDetailsResponse response =
                loanGoodsService.getLoanGoodsDetailsById(loanGoods.getId());

        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(response.getId()).isEqualTo(loanGoods.getId()),
                () -> assertThat(response.getName()).isEqualTo(loanGoods.getName()),
                () -> assertThat(response.getDescription()).isEqualTo(loanGoods.getDescription()),
                () -> assertThat(response.getContent()).isEqualTo(loanGoods.getContent()),
                () -> assertThat(response.getImageUrl()).isEqualTo(loanGoods.getImageUrl()),
                () ->
                        assertThat(response.getLoanGoodsType())
                                .isEqualTo(loanGoods.getLoanGoodsType()),
                () -> verify(loanGoodsRepository, times(1)).findById(loanGoods.getId()));
    }
}
