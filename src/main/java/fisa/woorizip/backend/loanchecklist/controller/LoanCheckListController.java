package fisa.woorizip.backend.loanchecklist.controller;

import fisa.woorizip.backend.loanchecklist.service.LoanCheckListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoanCheckListController {
    private final LoanCheckListService loanCheckListService;
}
