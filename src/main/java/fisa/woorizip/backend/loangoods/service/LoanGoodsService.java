package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailResponse;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;

import org.springframework.data.domain.Pageable;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailResponse;

public interface LoanGoodsService {

    ShowLoanGoodsResponse getLoanGoods(Pageable pageable);

    void deleteLoanGoods(Long id);

    ShowLoanGoodsDetailResponse getLoanGoodsDetailsById(Long loanGoodsId);
}
