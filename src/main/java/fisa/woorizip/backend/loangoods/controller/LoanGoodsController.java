package fisa.woorizip.backend.loangoods.controller;

import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailsResponse;
import fisa.woorizip.backend.loangoods.service.LoanGoodsService;
import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
public class LoanGoodsController {

    private final LoanGoodsService loanGoodsService;

    @GetMapping("/{loanGoodsId}")
    public ShowLoanGoodsDetailsResponse showLoanGoodsDetails(
            @PathVariable("loanGoodsId") Long loanGoodsId) {

        return loanGoodsService.getLoanGoodsDetailsById(loanGoodsId);
    }

    @Login
    @GetMapping("/recommendation")
    public List<ShowLoanGoodsDetailsResponse> showLoanGoodsRecommendations(
            @VerifiedMember MemberIdentity memberIdentity) {

        return loanGoodsService.getLoanGoodsRecommendations(memberIdentity);
    }
}
