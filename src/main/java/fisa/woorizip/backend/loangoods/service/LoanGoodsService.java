package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailResponse;

public interface LoanGoodsService {

    ShowLoanGoodsDetailResponse getLoanGoodsDetailsById(Long loanGoodsId);

}
