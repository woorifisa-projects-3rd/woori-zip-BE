package fisa.woorizip.backend.memberconsumption.controller;

import fisa.woorizip.backend.memberconsumption.service.MemberConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/consumption")
public class MemberConsumptionController {

    private final MemberConsumptionService memberConsumptionService;
}
