package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static fisa.woorizip.backend.loangoods.LoanGoodsErrorCode.LOAN_GOODS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LoanGoodsServiceImpl implements LoanGoodsService {

    private final LoanGoodsRepository loanGoodsRepository;

    @Override
    @Transactional(readOnly = true)
    public ShowLoanGoodsResponse getLoanGoods(Pageable pageable) {
        return ShowLoanGoodsResponse.from(loanGoodsRepository.findAllBy(pageable));
    }

    @Override
    @Transactional
    public void deleteLoanGoods(Long id) {
        LoanGoods loanGoods = findLoanGoodsById(id);
        loanGoodsRepository.delete(loanGoods);
    }

    private LoanGoods findLoanGoodsById(Long id) {
        return loanGoodsRepository
                .findById(id)
                .orElseThrow(() -> new WooriZipException(LOAN_GOODS_NOT_FOUND));
    }
}
