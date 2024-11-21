package fisa.woorizip.backend.loangoods.repository;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import java.util.List;

public interface LoanGoodsRepositoryCustom {

    List<LoanGoods> findLoanGoodsByCustomCriteria(
            long assets,
            long totalIncomeLastYear,
            Integer yearsOfMarriage,
            Integer creditScore,
            Integer monthsOfEmployment,
            Integer internationalAge);
}
