package fisa.woorizip.backend.loanchecklist;

import fisa.woorizip.backend.loanchecklist.dto.response.ShowLoanChecklistResponse;
import fisa.woorizip.backend.loanchecklist.service.LoanCheckListService;
import fisa.woorizip.backend.member.controller.auth.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fisa.woorizip.backend.member.domain.Role.ADMIN;

@RestController
@RequestMapping("/api/v1/loanchecklist")
@RequiredArgsConstructor
public class LoanChecklistController {

    private final LoanCheckListService loanCheckListService;

    @Login(role = ADMIN)
    @GetMapping("/{loanGoodsId}")
    public ShowLoanChecklistResponse showLoanChecklist(@PathVariable("loanGoodsId") Long loanGoodsId) {
        return loanCheckListService.getLoanChecklist(loanGoodsId);
    }
}
