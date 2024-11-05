package fisa.woorizip.backend.house.controller;

import fisa.woorizip.backend.house.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HouseController {

    private final HouseService houseService;
}
