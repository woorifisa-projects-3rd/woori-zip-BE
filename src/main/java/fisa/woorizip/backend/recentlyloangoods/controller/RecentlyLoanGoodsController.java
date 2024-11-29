package fisa.woorizip.backend.recentlyloangoods.controller;

import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;
import fisa.woorizip.backend.recentlyloangoods.dto.ShowRecentlyLoanGoodsResponse;
import fisa.woorizip.backend.recentlyloangoods.service.RecentlyLoanGoodsService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loan/recently")
@RequiredArgsConstructor
public class RecentlyLoanGoodsController {
    private final RecentlyLoanGoodsService recentlyLoanGoodsService;

    @Login
    @GetMapping
    public ShowRecentlyLoanGoodsResponse showRecentlyLoanGoods(
            @VerifiedMember MemberIdentity memberIdentity,
            @PageableDefault(size = 5) Pageable pageable) {
        return recentlyLoanGoodsService.getRecentlyLoanGoods(memberIdentity, pageable);
    }
}
