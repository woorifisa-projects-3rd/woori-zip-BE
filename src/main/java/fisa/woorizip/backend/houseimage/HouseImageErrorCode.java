package fisa.woorizip.backend.houseimage;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum HouseImageErrorCode implements ErrorCode {
    ;

    private final HttpStatus status;
    private final String message;

    HouseImageErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
