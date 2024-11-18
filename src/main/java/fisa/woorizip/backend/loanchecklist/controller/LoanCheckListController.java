package fisa.woorizip.backend.loanchecklist.controller;

import fisa.woorizip.backend.loanchecklist.dto.response.ShowLoanCheckListsResponse;
import fisa.woorizip.backend.loanchecklist.service.LoanCheckListService;
import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loanchecklists")
public class LoanCheckListController {
    private final LoanCheckListService loanCheckListService;

    @GetMapping
    public List<ShowLoanCheckListsResponse> getLoanCheckLists(
            @RequestParam("type") LoanGoodsType loanGoodsType) {
        return loanCheckListService.showLoanCheckLists(loanGoodsType);
    }
}
