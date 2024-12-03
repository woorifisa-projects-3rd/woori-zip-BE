package fisa.woorizip.backend.loanchecklist.controller;

import fisa.woorizip.backend.loanchecklist.dto.request.RecommendMemberInfoFilter;
import fisa.woorizip.backend.loanchecklist.service.LoanCheckListService;
import fisa.woorizip.backend.loangoods.dto.response.LoanGoodsResponse;
import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;
import fisa.woorizip.backend.member.domain.Role;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loanchecklist")
@RequiredArgsConstructor
public class LoanCheckListController {

    private final LoanCheckListService loanCheckListService;

    @Login(role = {Role.MEMBER, Role.ADMIN})
    @GetMapping
    public List<LoanGoodsResponse> showLoanGoodsRecommendFirstTime(
            @VerifiedMember MemberIdentity memberIdentity, @RequestParam("houseId") Long houseId) {

        return loanCheckListService.getLoanGoodsRecommendByMemberIdAndHouseId(houseId);
    }

    @Login(role = {Role.MEMBER, Role.ADMIN})
    @GetMapping("/recommend")
    public List<LoanGoodsResponse> showLoanGoodsRecommend(
            @VerifiedMember MemberIdentity memberIdentity,
            @RequestParam("houseId") Long houseId,
            @ModelAttribute @Valid RecommendMemberInfoFilter recommendMemberInfoFilter) {

        return loanCheckListService.getLoanGoodsRecommend(
                recommendMemberInfoFilter, memberIdentity, houseId);
    }
}
