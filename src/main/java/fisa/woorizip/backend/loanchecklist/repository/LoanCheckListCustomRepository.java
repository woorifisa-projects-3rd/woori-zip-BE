package fisa.woorizip.backend.loanchecklist.repository;

import fisa.woorizip.backend.loanchecklist.dto.LoanChecklistFilter;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import java.util.List;

public interface LoanCheckListCustomRepository {

    List<LoanGoods> findRecommendLoanGoods(LoanChecklistFilter filter);
}
