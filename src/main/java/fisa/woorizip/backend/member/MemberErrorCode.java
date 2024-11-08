package fisa.woorizip.backend.member;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum MemberErrorCode implements ErrorCode {
    EARNINGS_TYPE_NOT_FOUND(NOT_FOUND, "소득 유형이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    MemberErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
