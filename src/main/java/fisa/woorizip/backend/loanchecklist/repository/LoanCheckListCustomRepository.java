package fisa.woorizip.backend.loanchecklist.repository;

import fisa.woorizip.backend.loanchecklist.dto.request.RecommendLoanFilter;
import fisa.woorizip.backend.loanchecklist.dto.request.RecommendMemberInfoFilter;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import java.util.List;

public interface LoanCheckListCustomRepository {

    List<LoanGoods> findRecommendLoanGoodsFirstTime(RecommendLoanFilter recommendLoanFilter);

    List<LoanGoods> findRecommendLoanGoods(
            RecommendLoanFilter recommendLoanFilter,
            RecommendMemberInfoFilter recommendMemberInfoFilter);
}
