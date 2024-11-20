package fisa.woorizip.backend.house.controller;

import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.HouseDetailResponse;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.house.service.HouseService;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/houses")
public class HouseController {
    private final HouseService houseService;

    @GetMapping("/{houseId}")
    public HouseDetailResponse getHouseDetail(@PathVariable("houseId") Long houseId) {
        log.info("houseId {}", houseId);
        return houseService.getHouseDetail(houseId);
    }

    @GetMapping
    public ShowMapResponse ShowMap(
            @ModelAttribute @Valid MapFilterRequest mapFilterRequest,
            @VerifiedMember(required = false) MemberIdentity memberIdentity) {
        return houseService.showMap(mapFilterRequest, memberIdentity);
    }
}
