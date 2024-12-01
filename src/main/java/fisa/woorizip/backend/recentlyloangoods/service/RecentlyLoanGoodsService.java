package fisa.woorizip.backend.recentlyloangoods.service;

import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;

import org.springframework.data.domain.Pageable;

public interface RecentlyLoanGoodsService {

    ShowLoanGoodsResponse getRecentlyLoanGoods(
            MemberIdentity memberIdentity, Pageable pageable);
}
