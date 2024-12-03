package fisa.woorizip.backend.loanchecklist.repository;

import static fisa.woorizip.backend.loanchecklist.domain.MarriageStatus.NEW_MARRIAGE;
import static fisa.woorizip.backend.loanchecklist.domain.QLoanChecklist.loanChecklist;
import static fisa.woorizip.backend.loanchecklist.domain.WorkStatus.*;
import static fisa.woorizip.backend.loangoods.domain.QLoanGoods.loanGoods;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import fisa.woorizip.backend.loanchecklist.domain.MarriageStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkTerm;
import fisa.woorizip.backend.loanchecklist.dto.request.RecommendLoanFilter;
import fisa.woorizip.backend.loanchecklist.dto.request.RecommendMemberInfoFilter;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.member.domain.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class LoanCheckListCustomRepositoryImpl implements LoanCheckListCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LoanGoods> findRecommendLoanGoodsFirstTime(
            RecommendLoanFilter recommendLoanFilter) {

        return jpaQueryFactory
                .selectFrom(loanGoods)
                .join(loanChecklist)
                .on(loanChecklist.loanGoods.id.eq(loanGoods.id))
                .where(
                        depositGoe(recommendLoanFilter.getDeposit()),
                        monthlyRentGoe(recommendLoanFilter.getMonthlyRentFee()),
                        exclusiveAreaGoe(recommendLoanFilter.getExclusiveArea()))
                .fetch();
    }

    private BooleanExpression depositGoe(final Long deposit) {
        return (loanChecklist.leaseDeposit.goe(deposit).and(loanChecklist.leaseDeposit.isNotNull()))
                .or(loanChecklist.leaseDeposit.isNull());
    }

    private BooleanExpression monthlyRentGoe(final Long monthlyRentFee) {
        return (loanChecklist
                        .monthlyRent
                        .goe(monthlyRentFee)
                        .and(loanChecklist.monthlyRent.isNotNull()))
                .or(loanChecklist.monthlyRent.isNull());
    }

    private BooleanExpression exclusiveAreaGoe(final Double exclusiveArea) {

        return (loanChecklist
                        .exclusiveArea
                        .goe(exclusiveArea)
                        .and(loanChecklist.exclusiveArea.isNotNull()))
                .or(loanChecklist.exclusiveArea.isNull());
    }

    @Override
    public List<LoanGoods> findRecommendLoanGoods(
            RecommendLoanFilter recommendLoanFilter,
            RecommendMemberInfoFilter recommendMemberInfoFilter) {
        return jpaQueryFactory
                .selectFrom(loanGoods)
                .join(loanChecklist)
                .on(loanChecklist.loanGoods.id.eq(loanGoods.id))
                .where(
                        depositGoe(recommendLoanFilter.getDeposit()),
                        monthlyRentGoe(recommendLoanFilter.getMonthlyRentFee()),
                        exclusiveAreaGoe(recommendLoanFilter.getExclusiveArea()),
                        workStatusEq(
                                recommendMemberInfoFilter.getWorkStatus(),
                                recommendMemberInfoFilter.getWorkTerm()),
                        annualIncomeGoe(recommendMemberInfoFilter.getAnnualIncome()),
                        totalAssetsGoe(recommendMemberInfoFilter.getTotalAssets()),
                        marriageStatusEq(recommendMemberInfoFilter.getMarriageStatus()))
                .fetch();
    }

    private BooleanExpression workStatusEq(final WorkStatus workStatus, WorkTerm workTerm) {
        return (loanChecklist.workStatus.eq(workStatus))
                .and(loanChecklist.workTerm.loe(workTerm))
                .or(loanChecklist.workStatus.eq(ALL));
    }

    private BooleanExpression annualIncomeGoe(final Long annualIncome) {
        return (loanChecklist
                        .annualIncome
                        .goe(annualIncome)
                        .and(loanChecklist.annualIncome.isNotNull()))
                .or(loanChecklist.annualIncome.isNull());
    }

    private BooleanExpression totalAssetsGoe(final Long totalAssets) {

        return (loanChecklist
                        .totalAssets
                        .goe(totalAssets)
                        .and(loanChecklist.totalAssets.isNotNull()))
                .or(loanChecklist.totalAssets.isNull());
    }

    private BooleanExpression marriageStatusEq(final MarriageStatus marriageStatus) {

        return (loanChecklist
                        .marriageStatus
                        .eq(NEW_MARRIAGE)
                        .and(loanChecklist.marriageStatus.eq(marriageStatus)))
                .or(loanChecklist.marriageStatus.eq(MarriageStatus.ALL));
    }
}
