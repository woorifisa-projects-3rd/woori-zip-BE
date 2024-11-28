package fisa.woorizip.backend.recentlyloangoods.service;

import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.recentlyloangoods.dto.ShowRecentlyLoanGoodsResponse;
import org.springframework.data.domain.Pageable;

public interface RecentlyLoanGoodsService {

    ShowRecentlyLoanGoodsResponse getRecentlyLoanGoods(MemberIdentity memberIdentity, Pageable pageable);
}
