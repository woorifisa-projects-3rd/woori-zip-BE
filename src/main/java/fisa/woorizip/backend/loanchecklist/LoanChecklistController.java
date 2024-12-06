package fisa.woorizip.backend.loanchecklist;

import fisa.woorizip.backend.loanchecklist.dto.request.ModifyLoanChecklistRequest;
import fisa.woorizip.backend.loanchecklist.dto.response.ShowLoanChecklistResponse;
import fisa.woorizip.backend.loanchecklist.service.LoanCheckListService;
import fisa.woorizip.backend.member.controller.auth.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static fisa.woorizip.backend.member.domain.Role.ADMIN;

@RestController
@RequestMapping("/api/v1/loanchecklist")
@RequiredArgsConstructor
public class LoanChecklistController {

    private final LoanCheckListService loanCheckListService;

    @Login(role = ADMIN)
    @GetMapping("/{loanGoodsId}")
    public ShowLoanChecklistResponse showLoanChecklist(
            @PathVariable("loanGoodsId") Long loanGoodsId) {
        return loanCheckListService.getLoanChecklist(loanGoodsId);
    }

    @Login(role = ADMIN)
    @PutMapping("/{loanChecklistId}")
    public ResponseEntity<Void> modifyLoanChecklist(
            @PathVariable("loanChecklistId") Long loanChecklistId,
            ModifyLoanChecklistRequest modifyLoanChecklistRequest) {
        loanCheckListService.modifyLoanChecklist(loanChecklistId, modifyLoanChecklistRequest);
        return ResponseEntity.ok().build();
    }
}
