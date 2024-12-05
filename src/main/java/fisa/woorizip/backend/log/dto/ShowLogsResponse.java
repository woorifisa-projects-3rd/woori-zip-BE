package fisa.woorizip.backend.log.dto;

import fisa.woorizip.backend.log.domain.Log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ShowLogsResponse {

    private int page;
    private int totalPages;
    private List<LogResponse> logs;

    public static ShowLogsResponse from(Page<Log> logs) {
        return ShowLogsResponse.builder()
                .page(logs.getNumber())
                .totalPages(logs.getTotalPages())
                .logs(logs.getContent().stream().map(LogResponse::from).toList())
                .build();
    }
}
