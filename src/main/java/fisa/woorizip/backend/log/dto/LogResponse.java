package fisa.woorizip.backend.log.dto;

import fisa.woorizip.backend.log.domain.Log;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class LogResponse {

    private Long id;
    private String username;
    private LocalDateTime time;
    private String content;

    public static LogResponse from(Log log) {
        return LogResponse.builder()
                .id(log.getId())
                .username(log.getMember().getUsername())
                .time(log.getCreatedAt())
                .content(log.getContent())
                .build();

    }
}
