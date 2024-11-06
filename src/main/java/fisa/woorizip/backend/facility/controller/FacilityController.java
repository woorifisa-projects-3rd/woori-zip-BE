package fisa.woorizip.backend.facility.controller;

import fisa.woorizip.backend.facility.service.FacilityService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FacilityController {

    private final FacilityService facilityService;
}
