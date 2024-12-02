package fisa.woorizip.backend.loangoods.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.repository.RateRepository;
import fisa.woorizip.backend.recentlyloangoods.domain.RecentlyLoanGoods;
import fisa.woorizip.backend.recentlyloangoods.repository.RecentlyLoanGoodsRepository;
import fisa.woorizip.backend.support.fixture.LoanGoodsFixture;
import fisa.woorizip.backend.support.fixture.MemberFixture;
import fisa.woorizip.backend.support.fixture.RateFixture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LoanGoodsServiceTest {

    @Mock private LoanGoodsRepository loanGoodsRepository;
    @Mock private RecentlyLoanGoodsRepository recentlyLoanGoodsRepository;
    @Mock private RateRepository rateRepository;
    @Mock private MemberRepository memberRepository;

    @InjectMocks private LoanGoodsServiceImpl loanGoodsService;

    @Test
    void 대출상품아이디를_통해_상세정보를_조회한다() {

        LoanGoods loanGoods = LoanGoodsFixture.builder().id(1L).build();
        Rate rate = RateFixture.builder().loanGoods(loanGoods).build();
        Member member = MemberFixture.builder().id(1L).build();
        MemberIdentity memberIdentity =
                new MemberIdentity(member.getId(), member.getRole().toString());

        given(loanGoodsRepository.findById(loanGoods.getId())).willReturn(Optional.of(loanGoods));
        given(rateRepository.findByLoanGoodsId(loanGoods.getId())).willReturn(List.of(rate));
        given(memberRepository.findById(member.getId())).willReturn(Optional.of(member));

        ShowLoanGoodsDetailResponse response =
                loanGoodsService.getLoanGoodsDetailsById(loanGoods.getId(), memberIdentity);

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
                () ->
                        assertThat(response.getInterestMethod())
                                .isEqualTo(loanGoods.getInterestMethod()),
                () -> assertThat(response.getRateList().get(0).getId()).isEqualTo(rate.getId()),
                () ->
                        assertThat(response.getRateList().get(0).getRateType())
                                .isEqualTo(rate.getRateType()),
                () ->
                        assertThat(response.getRateList().get(0).getNormalRate())
                                .isEqualTo(rate.getNormalRate()),
                () ->
                        assertThat(response.getRateList().get(0).getSpecialRate())
                                .isEqualTo(rate.getSpecialRate()),
                () ->
                        assertThat(response.getInterestMethod())
                                .isEqualTo(loanGoods.getInterestMethod()),
                () -> verify(loanGoodsRepository, times(1)).findById(loanGoods.getId()),
                () -> verify(rateRepository, times(1)).findByLoanGoodsId(loanGoods.getId()),
                () ->
                        verify(recentlyLoanGoodsRepository, times(1))
                                .save(any(RecentlyLoanGoods.class)));
    }
}
