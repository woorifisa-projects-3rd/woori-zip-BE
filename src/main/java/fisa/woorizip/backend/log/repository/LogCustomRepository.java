package fisa.woorizip.backend.log.repository;

import fisa.woorizip.backend.log.domain.Log;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface LogCustomRepository {

    Page<Log> searchLogs(
            String username, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
