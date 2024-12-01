package fisa.woorizip.backend.loangoods.controller;

import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;
import fisa.woorizip.backend.loangoods.service.LoanGoodsService;
import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fisa.woorizip.backend.member.domain.Role.ADMIN;

@RestController
@RequestMapping("/api/v1/loangoods")
@RequiredArgsConstructor
public class LoanGoodsController {

    private final LoanGoodsService loanGoodsService;

    @Login(role = ADMIN)
    @GetMapping
    public ShowLoanGoodsResponse showLoanGoods(@VerifiedMember MemberIdentity memberIdentity, @PageableDefault(size = 5) Pageable pageable) {
        return loanGoodsService.getLoanGoods(pageable);
    }

}
