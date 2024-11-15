package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.support.fixture.LoanGoodsFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoanGoodsServiceImplTest {

    @Mock
    private LoanGoodsRepository loanGoodsRepository;

    @InjectMocks
    private LoanGoodsServiceImpl loanGoodsService;



    @Test
    void 대출상품_상세정보를_반환한다() {
        //given
        LoanGoods loanGoods = LoanGoodsFixture.builder().build();

        given(loanGoodsRepository.findById(loanGoods.getId())).willReturn(Optional.of(loanGoods));

        //when
        LoanGoods result = loanGoodsService.showLoanGoodsDetailsById(loanGoods.getId());

        //then
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.getId()).isEqualTo(loanGoods.getId()),
                () -> assertThat(result.getName()).isEqualTo(loanGoods.getName()),
                () -> assertThat(result.getDescription()).isEqualTo(loanGoods.getDescription()),
                () -> assertThat(result.getContent()).isEqualTo(loanGoods.getContent()),
                () -> assertThat(result.getImageUrl()).isEqualTo(loanGoods.getImageUrl()),
                () -> assertThat(result.getLoanGoodsType()).isEqualTo(loanGoods.getLoanGoodsType()),

                () -> verify(loanGoodsRepository,times(1)).findById(loanGoods.getId()));

    }
}
