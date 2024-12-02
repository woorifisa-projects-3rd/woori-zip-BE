package fisa.woorizip.backend.house.controller;

import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.HouseDetailResponse;
import fisa.woorizip.backend.house.dto.response.ShowAgentHouseResponse;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.house.service.HouseService;
import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import static fisa.woorizip.backend.member.domain.Role.AGENT;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HouseController {
    private final HouseService houseService;

    @GetMapping("/houses/{houseId}")
    public HouseDetailResponse getHouseDetail(@PathVariable("houseId") Long houseId) {
        log.info("houseId {}", houseId);
        return houseService.getHouseDetail(houseId);
    }

    @GetMapping("/houses")
    public ShowMapResponse ShowMap(
            @ModelAttribute @Valid MapFilterRequest mapFilterRequest,
            @VerifiedMember(required = false) MemberIdentity memberIdentity) {
        return houseService.showMap(mapFilterRequest, memberIdentity);
    }

    @Login(role = AGENT)
    @GetMapping("/agent/houses")
    public ShowAgentHouseResponse ShowAgentHouse(
            @VerifiedMember MemberIdentity memberIdentity,
            @PageableDefault(size = 6) Pageable pageable) {
        return houseService.getAgentHouse(memberIdentity, pageable);
    }
}
