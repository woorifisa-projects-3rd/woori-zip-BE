package fisa.woorizip.backend.house;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum HouseErrorCode implements ErrorCode {
    ;

    private final HttpStatus status;
    private final String message;

    HouseErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
