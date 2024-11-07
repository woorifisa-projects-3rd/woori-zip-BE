package fisa.woorizip.backend.log.controller;

import fisa.woorizip.backend.log.repository.LogRepository;
import fisa.woorizip.backend.log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LogController {
    private final LogService logService;
}
