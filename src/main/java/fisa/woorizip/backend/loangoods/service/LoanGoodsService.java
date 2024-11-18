package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailsResponse;

public interface LoanGoodsService {

    ShowLoanGoodsDetailsResponse getLoanGoodsDetailsById(Long loanGoodsId);
}
