package fisa.woorizip.backend.log.service;

import static fisa.woorizip.backend.common.exception.errorcode.CommonErrorCode.END_DATE_BEFORE_START_DATE;
import static fisa.woorizip.backend.log.LogErrorCode.LOG_NOT_FOUND;
import static java.util.Objects.isNull;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.log.domain.Log;
import fisa.woorizip.backend.log.dto.ShowLogResponse;
import fisa.woorizip.backend.log.dto.ShowLogsResponse;
import fisa.woorizip.backend.log.repository.LogRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private static final String NUMERIC_PATTERN = "-?\\d+";

    @Override
    @Transactional(readOnly = true)
    public ShowLogsResponse searchLogs(
            String keyword, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        validateEndDateBeforeStartDate(startDate, endDate);
        Long logId = null;
        String username = null;

        if (!isNull(keyword) && keyword.matches(NUMERIC_PATTERN)) {
            logId = Long.valueOf(keyword);
        } else username = keyword;

        Page<Log> logs = logRepository.searchLogs(logId, username, startDate, endDate, pageable);
        return ShowLogsResponse.from(logs);
    }

    @Override
    @Transactional(readOnly = true)
    public ShowLogResponse getLog(Long logId) {
        Log log = findLogById(logId);
        return ShowLogResponse.from(log);
    }

    private Log findLogById(Long logId) {
        return logRepository.findById(logId).orElseThrow(() -> new WooriZipException(LOG_NOT_FOUND));
    }

    private void validateEndDateBeforeStartDate(LocalDateTime startDate, LocalDateTime endDate) {
        if ((!isNull(startDate) && !isNull(endDate)) && endDate.isBefore(startDate)) {
            throw new WooriZipException(END_DATE_BEFORE_START_DATE);
        }
    }
}
