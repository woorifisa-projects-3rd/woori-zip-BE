package fisa.woorizip.backend.log;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum LogErrorCode implements ErrorCode {
    LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "로그를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    LogErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
