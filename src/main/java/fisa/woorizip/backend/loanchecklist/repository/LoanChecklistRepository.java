package fisa.woorizip.backend.loanchecklist.repository;

import fisa.woorizip.backend.loanchecklist.domain.LoanChecklist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanChecklistRepository
        extends JpaRepository<LoanChecklist, Long>, LoanCheckListCustomRepository {

    void deleteAllByLoanGoodsId(Long loanGoodsId);

    Optional<LoanChecklist> findByLoanGoodsId(Long loanGoodsId);
}
