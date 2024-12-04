package fisa.woorizip.backend.loangoods.service;

import static fisa.woorizip.backend.loangoods.LoanGoodsErrorCode.LOAN_GOODS_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailResponse;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.dto.response.RateResponse;
import fisa.woorizip.backend.rate.repository.RateRepository;
import fisa.woorizip.backend.recentlyloangoods.domain.RecentlyLoanGoods;
import fisa.woorizip.backend.recentlyloangoods.repository.RecentlyLoanGoodsRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanGoodsServiceImpl implements LoanGoodsService {

    private final LoanGoodsRepository loanGoodsRepository;
    private final RateRepository rateRepository;
    private final RecentlyLoanGoodsRepository recentlyLoanGoodsRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ShowLoanGoodsDetailResponse getLoanGoodsDetailsById(
            Long loanGoodsId, MemberIdentity memberIdentity) {

        LoanGoods loanGoods = findLoanGoodsById(loanGoodsId);
        List<RateResponse> rateList = getRateResponseList(loanGoodsId);
        saveRecentlyLoanGoods(memberIdentity.getId(), loanGoods);

        return ShowLoanGoodsDetailResponse.of(loanGoods, rateList);
    }

    private List<RateResponse> getRateResponseList(Long loanGoodsId) {
        List<Rate> rates = rateRepository.findByLoanGoodsId(loanGoodsId);
        List<RateResponse> rateList = rates.stream().map(RateResponse::from).toList();

        return rateList;
    }

    private void saveRecentlyLoanGoods(Long memberId, LoanGoods loanGoods) {
        Member member = findMemberById(memberId);
        RecentlyLoanGoods recentlyLoanGoods = createRecentlyLoanGoods(loanGoods, member);
        recentlyLoanGoodsRepository.save(recentlyLoanGoods);
    }

    private Member findMemberById(Long memberId) {
        Member member =
                memberRepository
                        .findById(memberId)
                        .orElseThrow(() -> new WooriZipException(MEMBER_NOT_FOUND));
        return member;
    }

    private static RecentlyLoanGoods createRecentlyLoanGoods(LoanGoods loanGoods, Member member) {
        RecentlyLoanGoods recentlyLoanGoods =
                RecentlyLoanGoods.builder()
                        .member(member)
                        .loanGoods(loanGoods)
                        .lookedAt(LocalDateTime.now())
                        .build();

        return recentlyLoanGoods;
    }

    @Override
    @Transactional(readOnly = true)
    public ShowLoanGoodsResponse getLoanGoods(Pageable pageable) {
        return ShowLoanGoodsResponse.from(loanGoodsRepository.findAllBy(pageable));
    }

    @Override
    @Transactional
    public void deleteLoanGoods(Long id) {
        LoanGoods loanGoods = findLoanGoodsById(id);
        loanGoodsRepository.delete(loanGoods);
    }

    private LoanGoods findLoanGoodsById(Long id) {
        return loanGoodsRepository
                .findById(id)
                .orElseThrow(() -> new WooriZipException(LOAN_GOODS_NOT_FOUND));
    }
}
