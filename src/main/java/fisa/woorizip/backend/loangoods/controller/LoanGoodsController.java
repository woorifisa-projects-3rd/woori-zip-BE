package fisa.woorizip.backend.loangoods.controller;

import static fisa.woorizip.backend.member.domain.Role.ADMIN;

import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailResponse;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;
import fisa.woorizip.backend.loangoods.service.LoanGoodsService;
import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;
import fisa.woorizip.backend.member.domain.Role;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loangoods")
@RequiredArgsConstructor
public class LoanGoodsController {

    private final LoanGoodsService loanGoodsService;

    @Login(role = {Role.MEMBER, Role.ADMIN})
    @GetMapping("/{loanGoodsId}")
    public ShowLoanGoodsDetailResponse showLoanGoodsDetails(
            @VerifiedMember MemberIdentity memberIdentity,
            @PathVariable("loanGoodsId") Long loanGoodsId) {

        return loanGoodsService.getLoanGoodsDetailsById(loanGoodsId, memberIdentity);
    }

    @Login(role = ADMIN)
    @GetMapping
    public ShowLoanGoodsResponse showLoanGoods(
            @VerifiedMember MemberIdentity memberIdentity,
            @PageableDefault(size = 5) Pageable pageable) {
        return loanGoodsService.getLoanGoods(pageable);
    }

    @Login(role = ADMIN)
    @DeleteMapping("/{loanGoodsId}")
    public ResponseEntity<Void> removeLoanGoods(
            @VerifiedMember MemberIdentity memberIdentity,
            @PathVariable("loanGoodsId") Long loanGoodsId) {
        loanGoodsService.deleteLoanGoods(loanGoodsId);
        return ResponseEntity.ok().build();
    }
}
