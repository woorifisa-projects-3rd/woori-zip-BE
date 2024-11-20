package fisa.woorizip.backend.loangoods.repository;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class LoanGoodsRepositoryCustomImpl implements LoanGoodsRepositoryCustom{

    private final EntityManager entityManager;

    public LoanGoodsRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<LoanGoods> findLoanGoodsByCustomCriteria(
            long assets,
            long totalIncomeLastYear,
            Integer yearsOfMarriage,
            Integer creditScore,
            Integer monthsOfEmployment,
            Integer internationalAge) {

        String jpql =
                "SELECT l FROM LoanGoods l "
                        + "WHERE l.maxAssets >= : assets "
                        + "AND l.maxTotalIncomeLastYear >= : totalIncomeLastYear "
                        + "AND l.maxYearsOfMarriage >= : yearsOfMarriage "
                        + "AND l.minCreditScore <= : creditScore "
                        + "AND l.minMonthsOfEmployment <= : monthsOfEmployment "
                        + "AND l.maxAge >= : internationalAge ";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("assets", assets);
        query.setParameter("totalIncomeLastYear", totalIncomeLastYear);
        query.setParameter("yearsOfMarriage", yearsOfMarriage);
        query.setParameter("creditScore", creditScore);
        query.setParameter("monthsOfEmployment", monthsOfEmployment);
        query.setParameter("internationalAge", internationalAge);

        return query.getResultList();
    }
}
