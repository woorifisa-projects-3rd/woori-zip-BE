package fisa.woorizip.backend.loangoods.repository;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanGoodsRepository extends JpaRepository<LoanGoods, Long>{

    Optional<LoanGoods> findLoanGoodsById(Long longGoodsId);

    @Query("SELECT l FROM LoanGoods l " +
                 "WHERE l.maxAssets >= : assets " +
                 "AND l.maxTotalIncomeLastYear >= : totalIncomeLastYear " +
                 "AND l.maxYearsOfMarriage >= : yearsOfMarriage " +
                 "AND l.minCreditScore <= : creditScore " +
                 "AND l.minMonthsOfEmployment <= : monthsOfEmployment " +
                 "AND l.maxAge >= : internationalAge ")
    List<LoanGoods> findLoanGoodsByMemberInformation(
         long assets,
         long totalIncomeLastYear,
         int yearsOfMarriage,
         int creditScore,
         int monthsOfEmployment,
         int internationalAge
     );
    }
