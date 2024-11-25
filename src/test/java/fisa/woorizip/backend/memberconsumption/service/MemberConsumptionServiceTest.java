package fisa.woorizip.backend.memberconsumption.service;

import fisa.woorizip.backend.consumptionanalysis.domain.ConsumptionAnalysis;
import fisa.woorizip.backend.consumptionanalysis.repository.ConsumptionAnalysisRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.memberconsumption.domain.MemberConsumption;
import fisa.woorizip.backend.memberconsumption.dto.response.CategoryResponse;
import fisa.woorizip.backend.memberconsumption.dto.response.ConsumptionAnalysisResponse;
import fisa.woorizip.backend.memberconsumption.repository.MemberConsumptionRepository;
import fisa.woorizip.backend.support.fixture.ConsumptionAnalysisFixture;
import fisa.woorizip.backend.support.fixture.MemberConsumptionFixture;
import fisa.woorizip.backend.support.fixture.MemberFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static fisa.woorizip.backend.facility.domain.Category.BOOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MemberConsumptionServiceTest {

    @Mock private MemberRepository memberRepository;
    @Mock private ConsumptionAnalysisRepository consumptionAnalysisRepository;
    @Mock private MemberConsumptionRepository memberConsumptionRepository;

    @InjectMocks private MemberConsumptionServiceImpl memberConsumptionService;

    @Test
    void 회원은_자신의_고객_유형과_소비_통계와_자신과_비슷한_유형의_평균_소비_통계와_자신에게_맞는_최적의_카테고리를_조회할_수_있다() {
        Member member = MemberFixture.builder().id(1L).build();
        MemberIdentity memberIdentity =
                new MemberIdentity(member.getId(), member.getRole().toString());
        ConsumptionAnalysis consumptionAnalysis =
                ConsumptionAnalysisFixture.builder().id(10L).build();
        MemberConsumption memberConsumption =
                MemberConsumptionFixture.builder().member(member).build();
        CategoryResponse bestCategory =
                CategoryResponse.of(
                        BOOK,
                        BigDecimal.valueOf(37.9228639)
                                .subtract(BigDecimal.valueOf(1.1741682974559686)));
        ConsumptionAnalysisResponse expected =
                ConsumptionAnalysisResponse.of(
                        consumptionAnalysis, memberConsumption, bestCategory);

        given(memberRepository.findById(any(Long.class))).willReturn(Optional.of(member));
        given(consumptionAnalysisRepository.findByCustomerType(any(String.class)))
                .willReturn(Optional.of(consumptionAnalysis));
        given(memberConsumptionRepository.findByMemberId(any(Long.class)))
                .willReturn(Optional.of(memberConsumption));

        ConsumptionAnalysisResponse result =
                memberConsumptionService.getConsumptionAnalysis(memberIdentity);

        assertAll(
                "result",
                () -> verify(memberRepository, times(1)).findById(member.getId()),
                () ->
                        verify(consumptionAnalysisRepository, times(1))
                                .findByCustomerType(consumptionAnalysis.getCustomerType()),
                () -> verify(memberConsumptionRepository, times(1)).findByMemberId(member.getId()),
                () ->
                        assertThat(expected.getMemberConsumption().getBookConsumption())
                                .isEqualTo(result.getMemberConsumption().getBookConsumption()),
                () ->
                        assertThat(expected.getOtherConsumption().getBookConsumption())
                                .isEqualTo(result.getOtherConsumption().getBookConsumption()),
                () ->
                        assertThat(expected.getBestCategory().getCategory())
                                .isEqualTo(result.getBestCategory().getCategory()),
                () ->
                        assertThat(expected.getBestCategory().getSubtract())
                                .isEqualTo(result.getBestCategory().getSubtract()));
    }
}
