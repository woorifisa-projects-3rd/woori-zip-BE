package fisa.woorizip.backend.house.controller;

import fisa.woorizip.backend.house.dto.MapFilterRequest;
import fisa.woorizip.backend.house.dto.ShowMapResponse;
import fisa.woorizip.backend.house.service.HouseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HouseController {

    private final HouseService houseService;

    @GetMapping("/houses")
    public List<ShowMapResponse> ShowMap(@ModelAttribute @Valid MapFilterRequest mapFilterRequest) {
        return null;
    }
}
