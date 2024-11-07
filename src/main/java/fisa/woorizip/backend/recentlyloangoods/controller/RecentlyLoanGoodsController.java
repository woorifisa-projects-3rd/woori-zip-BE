package fisa.woorizip.backend.recentlyloangoods.controller;

import fisa.woorizip.backend.recentlyloangoods.service.RecentlyLoanGoodsService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecentlyLoanGoodsController {
    private final RecentlyLoanGoodsService recentlyLoanGoodsService;
}
