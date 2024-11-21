package fisa.woorizip.backend.loangoods.service;

import static fisa.woorizip.backend.loangoods.LoanGoodsErrorCode.LOAN_GOODS_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailsResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;

import jakarta.persistence.EntityManager;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanGoodsServiceImpl implements LoanGoodsService {

    private final LoanGoodsRepository loanGoodsRepository;
    private final MemberRepository memberRepository;
    private final EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public ShowLoanGoodsDetailsResponse getLoanGoodsDetailsById(Long loanGoodsId) {

        LoanGoods loanGoods =
                loanGoodsRepository
                        .findLoanGoodsById(loanGoodsId)
                        .orElseThrow(() -> new WooriZipException(LOAN_GOODS_NOT_FOUND));

        return ShowLoanGoodsDetailsResponse.from(loanGoods);
    }

    @Override
    public List<ShowLoanGoodsDetailsResponse> getLoanGoodsRecommendations(
            MemberIdentity memberIdentity) {
        Long memberId = memberIdentity.getId();

        Member member =
                memberRepository
                        .findById(memberId)
                        .orElseThrow(() -> new WooriZipException(MEMBER_NOT_FOUND));

        int internationalAge = calculateInternationalAge(member.getBirthday());

        List<LoanGoods> loanGoodsList =
                loanGoodsRepository.findLoanGoodsByCustomCriteria(
                        member.getAssets(),
                        member.getTotalIncomeLastYear(),
                        member.getYearsOfMarriage(),
                        member.getCreditScore(),
                        member.getMonthsOfEmployment(),
                        internationalAge);

        if (loanGoodsList.isEmpty()) {
            throw new WooriZipException(LOAN_GOODS_NOT_FOUND);
        }

        return loanGoodsList.stream().map(ShowLoanGoodsDetailsResponse::from).toList();
    }

    public int calculateInternationalAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
