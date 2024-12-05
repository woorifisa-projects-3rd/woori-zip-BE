package fisa.woorizip.backend.log.dto;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.log.domain.Log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class LogResponse {

    private Long id;
    private boolean isSuccess;
    private String username;
    private String clientIp;
    private String requestUrl;
    private String responseStatus;
    private LocalDateTime createdAt;

    public static LogResponse from(Log log) {
        return LogResponse.builder()
                .id(log.getId())
                .isSuccess(log.isSuccess())
                .username(isNull(log.getMember()) ? null : log.getMember().getUsername())
                .clientIp(log.getClientIp())
                .requestUrl(log.getRequestUrl())
                .responseStatus(log.getResponseStatus())
                .createdAt(log.getCreatedAt())
                .build();
    }
}
