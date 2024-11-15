package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailsResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static fisa.woorizip.backend.loangoods.LoanGoodsErrorCode.LOAN_GOODS_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanGoodsServiceImpl implements LoanGoodsService {

    private final LoanGoodsRepository loanGoodsRepository;

    @Override
    @Transactional(readOnly = true)
    public ShowLoanGoodsDetailsResponse showLoanGoodsDetailsById(Long loanGoodsId) {

        LoanGoods loanGoods = loanGoodsRepository
                     .findById(loanGoodsId)
                     .orElseThrow(() -> new WooriZipException(LOAN_GOODS_NOT_FOUND));

        return ShowLoanGoodsDetailsResponse.from(loanGoods);
    }
}
