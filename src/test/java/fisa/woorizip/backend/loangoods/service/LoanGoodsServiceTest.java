package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.support.fixture.LoanGoodsFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoanGoodsServiceTest {

    @Mock private LoanGoodsRepository loanGoodsRepository;
    @InjectMocks private LoanGoodsServiceImpl loanGoodsService;

    @Test
    void 대출_상품을_전체_조회한다() {
        List<LoanGoods> loanGoodsList = List.of(LoanGoodsFixture.builder().id(1L).build());
        Pageable pageable = PageRequest.of(0, 5);
        Slice<LoanGoods> loanGoodsSlice = new SliceImpl<>(loanGoodsList, pageable, false);

        given(loanGoodsRepository.findAllBy(any(Pageable.class))).willReturn(loanGoodsSlice);

        ShowLoanGoodsResponse extend = ShowLoanGoodsResponse.from(loanGoodsSlice);
        ShowLoanGoodsResponse result = loanGoodsService.getLoanGoods(pageable);

        assertAll(
                () -> verify(loanGoodsRepository, times(1)).findAllBy(pageable),
                () -> assertThat(extend.isHasNext()).isEqualTo(result.isHasNext()),
                () ->
                        assertThat(extend.getRecentlyLoanGoods().size())
                                .isEqualTo(result.getRecentlyLoanGoods().size()),
                () ->
                        assertThat(extend.getRecentlyLoanGoods().get(0).getId())
                                .isEqualTo(result.getRecentlyLoanGoods().get(0).getId()));
    }
}
