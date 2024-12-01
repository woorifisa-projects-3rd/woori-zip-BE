package fisa.woorizip.backend.log.controller;

import fisa.woorizip.backend.log.dto.ShowLogsResponse;
import fisa.woorizip.backend.log.service.LogService;

import fisa.woorizip.backend.member.controller.auth.Login;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static fisa.woorizip.backend.member.domain.Role.ADMIN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LogController {

    private final LogService logService;

    @Login(role = ADMIN)
    @GetMapping("/logs")
    public ShowLogsResponse showLogs(@RequestParam("username") String username,
                                     @RequestParam("startDate") LocalDateTime startDate,
                                     @RequestParam("endDate") LocalDateTime endDate,
                                     Pageable pageable) {
        return logService.searchLogs(username, startDate, endDate, pageable);
    }
}
