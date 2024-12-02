package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;

import org.springframework.data.domain.Pageable;

public interface LoanGoodsService {

    ShowLoanGoodsResponse getLoanGoods(Pageable pageable);

    void deleteLoanGoods(Long id);
}
