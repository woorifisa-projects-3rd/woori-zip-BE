package fisa.woorizip.backend.loangoods.controller;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.service.LoanGoodsService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
public class LoanGoodsController {

    private final LoanGoodsService loanGoodsService;

    @GetMapping("/{loansId}")
    public ResponseEntity<LoanGoods> getLoanGoodsDetailsById(
            @PathVariable("loansId") Long loansId) {
        LoanGoods loanGoods = loanGoodsService.showLoanGoodsDetailsById(loansId);

        if (loanGoods == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(loanGoods);
    }
}
