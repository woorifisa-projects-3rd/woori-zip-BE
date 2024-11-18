package fisa.woorizip.backend.loanchecklist.repository;

import fisa.woorizip.backend.loanchecklist.domain.LoanCheckList;

import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanCheckListRepository extends JpaRepository<LoanCheckList, Long> {

    List<LoanCheckList> findLoanCheckListByLoanGoodsType (LoanGoodsType loanGoodsType);
}
