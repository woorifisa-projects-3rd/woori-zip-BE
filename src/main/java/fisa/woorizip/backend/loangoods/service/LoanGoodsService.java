package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailsResponse;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;

import java.util.List;

public interface LoanGoodsService {

    ShowLoanGoodsDetailsResponse getLoanGoodsDetailsById(Long loanGoodsId);

    List<ShowLoanGoodsDetailsResponse> getLoanGoodsRecommendations (MemberIdentity memberIdentity);


}
