package fisa.woorizip.backend.loangoods.controller;

import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailResponse;
import fisa.woorizip.backend.loangoods.service.LoanGoodsService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
public class LoanGoodsController {

    private final LoanGoodsService loanGoodsService;

    @GetMapping("/{loanGoodsId}")
    public ShowLoanGoodsDetailResponse showLoanGoodsDetails(
            @PathVariable("loanGoodsId") Long loanGoodsId) {

        return loanGoodsService.getLoanGoodsDetailsById(loanGoodsId);
    }
}
