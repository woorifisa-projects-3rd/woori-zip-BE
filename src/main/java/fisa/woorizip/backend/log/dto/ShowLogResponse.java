package fisa.woorizip.backend.log.dto;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.log.domain.Log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
@Builder
public class ShowLogResponse {

    private Long id;
    private boolean isSuccess;
    private String username;
    private String clientIp;
    private String requestUrl;
    private String requestBody;
    private String responseStatus;
    private String responseBody;
    private LocalDateTime createdAt;

    public static ShowLogResponse from(Log log) {
        return ShowLogResponse.builder()
                .id(log.getId())
                .isSuccess(log.isSuccess())
                .username(isNull(log.getMember()) ? null : log.getMember().getUsername())
                .clientIp(log.getClientIp())
                .requestUrl(log.getRequestUrl())
                .requestBody(log.getRequestBody())
                .responseStatus(log.getResponseStatus())
                .responseBody(log.getResponseBody())
                .createdAt(log.getCreatedAt())
                .build();
    }
}
