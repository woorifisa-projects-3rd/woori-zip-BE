package fisa.woorizip.backend.rate;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum RateErrorCode implements ErrorCode {
    RATE_NOT_FOUND(NOT_FOUND, "금리 정보가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    RateErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
