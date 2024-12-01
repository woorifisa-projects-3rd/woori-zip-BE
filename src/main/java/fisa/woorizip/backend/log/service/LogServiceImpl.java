package fisa.woorizip.backend.log.service;

import fisa.woorizip.backend.log.domain.Log;
import fisa.woorizip.backend.log.dto.ShowLogsResponse;
import fisa.woorizip.backend.log.repository.LogRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Override
    public ShowLogsResponse searchLogs(String username, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        Page<Log> logs = logRepository.searchLogs(username, startDate, endDate, pageable);
        return ShowLogsResponse.from(logs);
    }
}
