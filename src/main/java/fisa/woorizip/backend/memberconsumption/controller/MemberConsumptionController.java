package fisa.woorizip.backend.memberconsumption.controller;

import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;
import fisa.woorizip.backend.memberconsumption.dto.response.ConsumptionAnalysisResponse;
import fisa.woorizip.backend.memberconsumption.service.MemberConsumptionService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/consumption")
public class MemberConsumptionController {

    private final MemberConsumptionService memberConsumptionService;

    @GetMapping
    @Login
    public ConsumptionAnalysisResponse showConsumptionAnalysis(
            @VerifiedMember MemberIdentity memberIdentity) {
        return memberConsumptionService.getConsumptionAnalysis(memberIdentity);
    }
}
