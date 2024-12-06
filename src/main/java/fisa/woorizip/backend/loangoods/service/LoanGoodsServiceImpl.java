package fisa.woorizip.backend.loangoods.service;

import static fisa.woorizip.backend.loangoods.LoanGoodsErrorCode.LOAN_GOODS_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;
import static fisa.woorizip.backend.rate.RateErrorCode.RATE_NOT_FOUND;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.loanchecklist.domain.LoanChecklist;
import fisa.woorizip.backend.loanchecklist.dto.request.LoanChecklistRequest;
import fisa.woorizip.backend.loanchecklist.repository.LoanChecklistRepository;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.request.ModifyLoanGoodsRequest;
import fisa.woorizip.backend.loangoods.dto.request.SaveLoanGoodsRequest;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailResponse;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.dto.request.RateRequest;
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
    private final LoanChecklistRepository loanChecklistRepository;

    @Override
    @Transactional
    public ShowLoanGoodsDetailResponse getLoanGoodsDetailsById(
            Long loanGoodsId, MemberIdentity memberIdentity) {

        LoanGoods loanGoods = findLoanGoodsById(loanGoodsId);
        List<RateResponse> rateList = getRateResponseList(loanGoodsId);
        saveRecentlyLoanGoods(memberIdentity.getId(), loanGoods);

        return ShowLoanGoodsDetailResponse.of(loanGoods, rateList);
    }

    @Override
    @Transactional
    public void updateLoanGoods(Long loanGoodsId, ModifyLoanGoodsRequest modifyLoanGoodsRequest) {
        LoanGoods loanGoods = findLoanGoodsById(loanGoodsId);
        loanGoods.updateLoanGoods(modifyLoanGoodsRequest.toLoanGoods());
        if (!isNull(modifyLoanGoodsRequest.getRateRequests()))
            updateRates(modifyLoanGoodsRequest.getRateRequests());
    }

    private void updateRates(List<RateRequest> rateRequests) {
        rateRequests.forEach(
                rateRequest -> {
                    Rate rate = findRateById(rateRequest.getId());
                    rate.updateRate(rateRequest.toRate());
                });
    }

    private Rate findRateById(Long rateId) {
        return rateRepository
                .findById(rateId)
                .orElseThrow(() -> new WooriZipException(RATE_NOT_FOUND));
    }

    private List<Rate> findRatesByLoanGoodsId(Long loanGoodsId) {
        return rateRepository.findAllByLoanGoodsId(loanGoodsId);
    }

    @Override
    @Transactional
    public void saveLoanGoods(SaveLoanGoodsRequest saveLoanGoodsRequest) {
        LoanGoods loanGoods = loanGoodsRepository.save(saveLoanGoodsRequest.toLoanGoods());
        saveRates(saveLoanGoodsRequest.getRateRequests(), loanGoods);
        saveLoanChecklist(saveLoanGoodsRequest.getLoanChecklistRequest(), loanGoods);
    }

    private void saveRates(List<RateRequest> rateRequests, LoanGoods loanGoods) {
        if (isNull(rateRequests)) return;
        List<Rate> rates = rateRequests.stream().map(rate -> rate.toRate(loanGoods)).toList();
        rateRepository.saveAll(rates);
    }

    private void saveLoanChecklist(LoanChecklistRequest loanChecklistRequest, LoanGoods loanGoods) {
        LoanChecklist loanChecklist = loanChecklistRequest.toLoanChecklist(loanGoods);
        loanChecklistRepository.save(loanChecklist);
    }

    private List<RateResponse> getRateResponseList(Long loanGoodsId) {
        List<Rate> rates = rateRepository.findAllByLoanGoodsId(loanGoodsId);
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
    public void removeLoanGoods(Long loanGoodsId) {
        LoanGoods loanGoods = findLoanGoodsById(loanGoodsId);
        rateRepository.deleteAllByLoanGoodsId(loanGoodsId);
        loanChecklistRepository.deleteAllByLoanGoodsId(loanGoodsId);
        loanGoodsRepository.delete(loanGoods);
    }

    private LoanGoods findLoanGoodsById(Long id) {
        return loanGoodsRepository
                .findById(id)
                .orElseThrow(() -> new WooriZipException(LOAN_GOODS_NOT_FOUND));
    }
}
