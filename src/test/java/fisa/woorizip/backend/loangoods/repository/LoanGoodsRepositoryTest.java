package fisa.woorizip.backend.loangoods.repository;

import fisa.woorizip.backend.loanchecklist.domain.LoanChecklist;
import fisa.woorizip.backend.loanchecklist.repository.LoanChecklistRepository;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.support.RepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryTest
public class LoanGoodsRepositoryTest {
    @Autowired private LoanChecklistRepository loanChecklistRepository;
    @Autowired private LoanGoodsRepository loanGoodsRepository;

    private LoanGoods save(LoanGoods loanGoods) {
        return loanGoodsRepository.save(loanGoods);
    }

    private LoanChecklist save(LoanChecklist loanChecklist) {
        return loanChecklistRepository.save(loanChecklist);
    }

    private LoanCheck
}
