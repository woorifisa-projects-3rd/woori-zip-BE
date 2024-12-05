package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.dto.request.LoanGoodsRequest;
import fisa.woorizip.backend.loangoods.dto.request.SaveLoanGoodsRequest;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailResponse;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;

import org.springframework.data.domain.Pageable;

public interface LoanGoodsService {

    ShowLoanGoodsResponse getLoanGoods(Pageable pageable);

    void deleteLoanGoods(Long id);

    ShowLoanGoodsDetailResponse getLoanGoodsDetailsById(Long loanGoodsId, MemberIdentity member);

    void updateLoanGoods(Long loanGoodsId, LoanGoodsRequest loanGoodsRequest);

    void saveLoanGoods(SaveLoanGoodsRequest saveLoanGoodsRequest);
}
