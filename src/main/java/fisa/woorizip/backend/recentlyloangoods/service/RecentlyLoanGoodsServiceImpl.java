package fisa.woorizip.backend.recentlyloangoods.service;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;
import fisa.woorizip.backend.recentlyloangoods.repository.RecentlyLoanGoodsRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecentlyLoanGoodsServiceImpl implements RecentlyLoanGoodsService {
    private final RecentlyLoanGoodsRepository recentlyLoanGoodsRepository;

    @Override
    @Transactional(readOnly = true)
    public ShowLoanGoodsResponse getRecentlyLoanGoods(
            MemberIdentity memberIdentity, Pageable pageable) {
        Slice<LoanGoods> loanGoods =
                recentlyLoanGoodsRepository.findLoanGoodsByMemberId(
                        memberIdentity.getId(), pageable);
        return ShowLoanGoodsResponse.from(loanGoods);
    }
}
