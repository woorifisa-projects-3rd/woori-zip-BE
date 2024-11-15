package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailsResponse;

public interface LoanGoodsService {

    ShowLoanGoodsDetailsResponse showLoanGoodsDetailsById(Long id);
}
