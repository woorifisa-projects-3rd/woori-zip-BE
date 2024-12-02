package fisa.woorizip.backend.log.controller;

import fisa.woorizip.backend.log.dto.ShowLogsResponse;
import fisa.woorizip.backend.log.service.LogService;

import fisa.woorizip.backend.member.controller.auth.Login;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static fisa.woorizip.backend.member.domain.Role.ADMIN;
import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LogController {

    private final LogService logService;
    private final static String LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @Login(role = ADMIN)
    @GetMapping("/logs")
    public ShowLogsResponse showLogs(@RequestParam(value = "username", required = false) String keyword,
                                     @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = LOCAL_DATE_TIME_FORMAT) LocalDateTime startDate,
                                     @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = LOCAL_DATE_TIME_FORMAT) LocalDateTime endDate,
                                     Pageable pageable) {
        return logService.searchLogs(keyword, startDate, endDate, pageable);
    }
}
