package fisa.woorizip.backend.loangoods.controller;

import fisa.woorizip.backend.loangoods.service.LoanGoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoanGoodsController {

    private final LoanGoodsService loanGoodsService;

}
