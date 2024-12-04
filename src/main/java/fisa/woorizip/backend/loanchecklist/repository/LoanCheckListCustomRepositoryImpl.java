package fisa.woorizip.backend.loanchecklist.repository;

import static fisa.woorizip.backend.loanchecklist.domain.MarriageStatus.NONE_MARRIAGE;
import static fisa.woorizip.backend.loanchecklist.domain.QLoanChecklist.loanChecklist;
import static fisa.woorizip.backend.loanchecklist.domain.WorkStatus.NONE_WORK_STATUS;
import static fisa.woorizip.backend.loanchecklist.domain.WorkTerm.NONE_TERM;
import static fisa.woorizip.backend.loanchecklist.domain.WorkTerm.ONE_YEAR;
import static fisa.woorizip.backend.loangoods.domain.QLoanGoods.loanGoods;
import static java.util.Objects.isNull;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import fisa.woorizip.backend.loanchecklist.domain.MarriageStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkStatus;
import fisa.woorizip.backend.loanchecklist.domain.WorkTerm;
import fisa.woorizip.backend.loanchecklist.dto.LoanCheckListFilter;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class LoanCheckListCustomRepositoryImpl implements LoanCheckListCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LoanGoods> findRecommendLoanGoods(LoanCheckListFilter filter) {
        return jpaQueryFactory
                .selectFrom(loanGoods)
                .join(loanChecklist)
                .on(loanChecklist.loanGoods.id.eq(loanGoods.id))
                .where(
                        workStatusEq(filter.getWorkStatus()),
                        workTermEq(filter.getWorkTerm()),
                        annualIncomeEq(filter.getAnnualIncome()),
                        totalAssetsEq(filter.getTotalAssets()),
                        contractEq(filter.getContract()),
                        marriageStatusEq(filter.getMarriageStatus()),
                        leaseDepositEq(filter.getLeaseDeposit()),
                        monthlyRentEq(filter.getMonthlyRent()),
                        exclusiveAreaEq(filter.getExclusiveArea()))
                .stream()
                .toList();
    }

    private BooleanExpression workStatusEq(WorkStatus workStatus) {
        return loanChecklist
                .workStatus
                .eq(NONE_WORK_STATUS)
                .or(loanChecklist.workStatus.eq(workStatus));
    }

    private BooleanExpression workTermEq(WorkTerm workTerm) {
        if (workTerm == ONE_YEAR) return null;
        return loanChecklist.workTerm.eq(NONE_TERM).or(loanChecklist.workTerm.eq(workTerm));
    }

    private BooleanExpression annualIncomeEq(Long annualIncome) {
        if (isNull(annualIncome)) return null;
        return loanChecklist
                .annualIncome
                .isNull()
                .or(
                        loanChecklist
                                .annualIncome
                                .isNotNull()
                                .and(loanChecklist.annualIncome.goe(annualIncome)));
    }

    private BooleanExpression totalAssetsEq(Long totalAssets) {
        if (isNull(totalAssets)) return null;
        return loanChecklist
                .totalAssets
                .isNull()
                .or(
                        loanChecklist
                                .totalAssets
                                .isNotNull()
                                .and(loanChecklist.totalAssets.goe(totalAssets)));
    }

    private BooleanExpression contractEq(Boolean contract) {
        if (isNull(contract)) return null;
        return loanChecklist.contract.eq(contract);
    }

    private BooleanExpression marriageStatusEq(MarriageStatus marriageStatus) {
        return loanChecklist
                .marriageStatus
                .eq(NONE_MARRIAGE)
                .or(loanChecklist.marriageStatus.eq(marriageStatus));
    }

    private BooleanExpression leaseDepositEq(Long leaseDeposit) {
        if (isNull(leaseDeposit)) return null;
        return loanChecklist
                .leaseDeposit
                .isNull()
                .or(
                        loanChecklist
                                .leaseDeposit
                                .isNotNull()
                                .and(loanChecklist.leaseDeposit.goe(leaseDeposit)));
    }

    private BooleanExpression monthlyRentEq(Long monthlyRent) {
        if (isNull(monthlyRent)) return null;
        return loanChecklist
                .monthlyRent
                .isNull()
                .or(
                        loanChecklist
                                .monthlyRent
                                .isNotNull()
                                .and(loanChecklist.monthlyRent.goe(monthlyRent)));
    }

    private BooleanExpression exclusiveAreaEq(Double exclusiveArea) {
        if (isNull(exclusiveArea)) return null;
        return loanChecklist
                .exclusiveArea
                .isNull()
                .or(
                        loanChecklist
                                .exclusiveArea
                                .isNotNull()
                                .and(loanChecklist.exclusiveArea.goe(exclusiveArea)));
    }
}
