package fisa.woorizip.backend.log.service;

import fisa.woorizip.backend.log.dto.ShowLogsResponse;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface LogService {
    ShowLogsResponse searchLogs(
            String username, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
