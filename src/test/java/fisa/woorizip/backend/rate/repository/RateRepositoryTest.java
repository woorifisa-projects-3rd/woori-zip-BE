package fisa.woorizip.backend.rate.repository;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.LoanGoodsFixture;
import fisa.woorizip.backend.support.fixture.RateFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
class RateRepositoryTest {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private LoanGoodsRepository loanGoodsRepository;


    @Test
    void 전세자금대출_대출상품일때_금리테이블에서_해당하는_금리목록을_조회한다() {

        LoanGoods loanGoods = LoanGoodsFixture.builder().build();
        loanGoodsRepository.save(loanGoods);

        Rate rate = RateFixture.builder().loanGoods(loanGoods).build();
        rateRepository.save(rate);

        List<Rate> result = rateRepository.findByLoanGoodsIdWithFetchJoin(loanGoods);

        assertAll(
                () -> {
                    Rate fetchedRate = result.get(0);
                    assertThat(fetchedRate.getLoanGoods().getId()).isEqualTo(loanGoods.getId());
                    assertThat(fetchedRate.getBasic()).isEqualTo(rate.getBasic());
                    assertThat(fetchedRate.getNormal()).isEqualTo(rate.getNormal());
                    assertThat(fetchedRate.getSpecial()).isEqualTo(rate.getSpecial());
                    assertThat(fetchedRate.getMin()).isEqualTo(rate.getMin());
                });
    }
}