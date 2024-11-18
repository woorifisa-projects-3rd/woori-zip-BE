package fisa.woorizip.backend.loangoods.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.LoanGoodsFixture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RepositoryTest
class LoanGoodsRepositoryTest {

    @Autowired private LoanGoodsRepository loanGoodsRepository;

    private LoanGoods 저장(LoanGoods loanGoods) {
        return loanGoodsRepository.save(loanGoods);
    }

    @Test
    void findById_존재하는_대출상품을_조회할_수_있다() {

        LoanGoods loanGoods = 저장(LoanGoodsFixture.builder().build());

        Optional<LoanGoods> findLoanGoods =
                loanGoodsRepository.findLoanGoodsById(loanGoods.getId());

        assertAll(
                () -> {
                    LoanGoods loanGoodsValue = findLoanGoods.get();
                    assertThat(loanGoodsValue.getId()).isEqualTo(loanGoods.getId());
                    assertThat(loanGoodsValue.getName()).isEqualTo(loanGoods.getName());
                    assertThat(loanGoodsValue.getDescription())
                            .isEqualTo(loanGoods.getDescription());
                    assertThat(loanGoodsValue.getContent()).isEqualTo(loanGoods.getContent());
                    assertThat(loanGoodsValue.getImageUrl()).isEqualTo(loanGoods.getImageUrl());
                    assertThat(loanGoodsValue.getLoanGoodsType())
                            .isEqualTo(loanGoods.getLoanGoodsType());
                });
    }

    @Test
    void findById_존재하지_않는_대출상품을_조회할_수_없다() {
        Long nonExistId = 100000L;

        Optional<LoanGoods> findLoanGoods = loanGoodsRepository.findLoanGoodsById(nonExistId);

        assertThat(findLoanGoods).isNotPresent();
    }
}
