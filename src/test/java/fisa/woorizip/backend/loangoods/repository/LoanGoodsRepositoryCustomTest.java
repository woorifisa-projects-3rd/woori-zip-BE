package fisa.woorizip.backend.loangoods.repository;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.LoanGoodsFixture;
import fisa.woorizip.backend.support.fixture.MemberFixture;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
public class LoanGoodsRepositoryCustomTest {
    @Autowired
    private LoanGoodsRepository loanGoodsRepository;

    @Autowired
    private EntityManager entityManager;

    private LoanGoods 저장(LoanGoods loanGoods) {
        return loanGoodsRepository.save(loanGoods);
    }


    @Test
    void 사용자에_맞는_대출상품을_조회할_수_있다() {

        LoanGoods loanGoods = 저장(LoanGoodsFixture.builder().build());
        Member member = MemberFixture.builder().build();

        int internationalAge =Period.between(member.getBirthday(), LocalDate.now()).getYears();

        List<LoanGoods> findLoanGoods =
                loanGoodsRepository.findLoanGoodsByCustomCriteria(
                        member.getAssets(),
                        member.getTotalIncomeLastYear(),
                        member.getYearsOfMarriage(),
                        member.getCreditScore(),
                        member.getMonthsOfEmployment(),
                        internationalAge);

        assertAll(
                () -> {
                    assertThat(findLoanGoods).isNotEmpty();
                    findLoanGoods.forEach(loanGoodsValue -> {
                        assertThat(loanGoodsValue.getMaxAssets()).isGreaterThanOrEqualTo(member.getAssets());
                        assertThat(loanGoodsValue.getMaxTotalIncomeLastYear()).isGreaterThanOrEqualTo(member.getTotalIncomeLastYear());
                        assertThat(loanGoods.getMaxYearsOfMarriage()).isGreaterThanOrEqualTo(member.getYearsOfMarriage());
                        assertThat(loanGoods.getMinCreditScore()).isLessThanOrEqualTo(member.getCreditScore());
                        assertThat(loanGoods.getMinMonthsOfEmployment()).isLessThanOrEqualTo(member.getMonthsOfEmployment());
                        assertThat(loanGoods.getMaxAge()).isGreaterThanOrEqualTo(internationalAge);
                    });

                });
    }

    @Test
    void 사용자에_맞는_대출상품이_없다() {

        LoanGoods loanGoods = 저장(LoanGoodsFixture.builder().build());
        Member member = MemberFixture.builder()
                .assets(50000000L)
                .totalIncomeLastYear(10000000L)
                .yearsOfMarriage(10)
                .creditScore(500)
                .monthsOfEmployment(6)
                .build();

        int internationalAge =Period.between(member.getBirthday(), LocalDate.now()).getYears();

        List<LoanGoods> findLoanGoods =
                loanGoodsRepository.findLoanGoodsByCustomCriteria(
                        member.getAssets(),
                        member.getTotalIncomeLastYear(),
                        member.getYearsOfMarriage(),
                        member.getCreditScore(),
                        member.getMonthsOfEmployment(),
                        internationalAge);

        assertThat(findLoanGoods).isEmpty();
    }
}

