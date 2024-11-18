package fisa.woorizip.backend.house.controller;

import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.house.service.HouseService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HouseController {

    private final HouseService houseService;

    @GetMapping("/houses")
    public ShowMapResponse ShowMap(@ModelAttribute @Valid MapFilterRequest mapFilterRequest) {
        return houseService.showMap(mapFilterRequest);
    }
}
