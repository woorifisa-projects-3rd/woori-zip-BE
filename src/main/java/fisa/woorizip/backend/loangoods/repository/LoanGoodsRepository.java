package fisa.woorizip.backend.loangoods.repository;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoanGoodsRepository extends JpaRepository<LoanGoods, Long> {

    Optional<LoanGoods> findLoanGoodsById(Long longGoodsId);

    @Query(
            "SELECT l FROM LoanGoods l "
                    + "WHERE l.maxAssets >= :assets "
                    + "AND l.maxTotalIncomeLastYear >= :totalIncomeLastYear "
                    + "AND l.maxYearsOfMarriage >= :yearsOfMarriage "
                    + "AND l.minCreditScore <= :creditScore "
                    + "AND l.minMonthsOfEmployment <= :monthsOfEmployment "
                    + "AND l.maxAge >= :internationalAge "
                    + "ORDER BY l.interestRate")
    List<LoanGoods> findLoanGoodsByMemberInformation(
            @Param("assets") long assets,
            @Param("totalIncomeLastYear") long totalIncomeLastYear,
            @Param("yearsOfMarriage") int yearsOfMarriage,
            @Param("creditScore") int creditScore,
            @Param("monthsOfEmployment") int monthsOfEmployment,
            @Param("internationalAge") int internationalAge);
}
