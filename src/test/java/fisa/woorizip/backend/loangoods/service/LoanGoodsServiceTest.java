package fisa.woorizip.backend.loangoods.service;

import static fisa.woorizip.backend.member.domain.Role.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Arrays;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailsResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.support.fixture.LoanGoodsFixture;

import fisa.woorizip.backend.support.fixture.MemberFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LoanGoodsServiceTest {

    @Mock private LoanGoodsRepository loanGoodsRepository;
    @Mock private MemberRepository memberRepository;
    @InjectMocks private LoanGoodsServiceImpl loanGoodsService;

    @Test
    void 대출상품_상세정보를_반환한다() {
        LoanGoods loanGoods = LoanGoodsFixture.builder().build();

        given(loanGoodsRepository.findLoanGoodsById(loanGoods.getId()))
                .willReturn(Optional.of(loanGoods));

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
                                .isEqualTo(loanGoods.getLoanGoodsType()));

        verify(loanGoodsRepository, times(1)).findLoanGoodsById(loanGoods.getId());
    }

    @Test
    void 사용자에_맞는_대출상품을_반환한다() {

        Member member = MemberFixture.builder().id(1L).role(Role.MEMBER).build();
        MemberIdentity memberIdentity = new MemberIdentity(member.getId(),member.getRole().name());
        List<LoanGoods> loanGoodsList = Arrays.asList(
                LoanGoodsFixture.builder().build()
        );
        int expectedAge = 34;
        given(memberRepository.findById(memberIdentity.getId()))
                .willReturn(Optional.of(member));
        given(loanGoodsRepository.findLoanGoodsByCustomCriteria(
                member.getAssets(),
                member.getTotalIncomeLastYear(),
                member.getYearsOfMarriage(),
                member.getCreditScore(),
                member.getMonthsOfEmployment(),
                expectedAge))
                 .willReturn(loanGoodsList);

        List<ShowLoanGoodsDetailsResponse> responses
                = loanGoodsService.getLoanGoodsRecommendations(memberIdentity);

        assertThat(responses.get(0).getId()).isEqualTo(loanGoodsList.get(0).getId());

        verify(memberRepository, times(1)).findById(memberIdentity.getId());
        verify(loanGoodsRepository, times(1))
                .findLoanGoodsByCustomCriteria(
                        member.getAssets(),
                        member.getTotalIncomeLastYear(),
                        member.getYearsOfMarriage(),
                        member.getCreditScore(),
                        member.getMonthsOfEmployment(),
                        expectedAge);
    }

    @Test
    void 사용자에게_맞는_대출상품이_없다 () {

        Member member = MemberFixture.builder().id(1L).role(Role.MEMBER).build();
        MemberIdentity memberIdentity = new MemberIdentity(member.getId(),member.getRole().name());

        int expectedAge = 34;

        given(memberRepository.findById(memberIdentity.getId()))
                .willReturn(Optional.of(member));
        given(loanGoodsRepository.findLoanGoodsByCustomCriteria(
                member.getAssets(),
                member.getTotalIncomeLastYear(),
                member.getYearsOfMarriage(),
                member.getCreditScore(),
                member.getMonthsOfEmployment(),
                expectedAge))
                .willReturn(Collections.emptyList());

        assertThrows(WooriZipException.class, () -> {
            loanGoodsService.getLoanGoodsRecommendations(memberIdentity);
        });

        verify(memberRepository, times(1)).findById(memberIdentity.getId());
        verify(loanGoodsRepository, times(1)).findLoanGoodsByCustomCriteria(
                member.getAssets(),
                member.getTotalIncomeLastYear(),
                member.getYearsOfMarriage(),
                member.getCreditScore(),
                member.getMonthsOfEmployment(),
                expectedAge
        );

    }



}
