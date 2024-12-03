package fisa.woorizip.backend.loanchecklist.service;

import fisa.woorizip.backend.loanchecklist.dto.request.RecommendMemberInfoFilter;
import fisa.woorizip.backend.loangoods.dto.response.LoanGoodsResponse;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;

import java.util.List;

public interface LoanCheckListService {

    List<LoanGoodsResponse> getLoanGoodsRecommendByMemberIdAndHouseId(Long houseId);


    List<LoanGoodsResponse> getLoanGoodsRecommend(RecommendMemberInfoFilter recommendMemberInfoFilter, Long houseId);
}
