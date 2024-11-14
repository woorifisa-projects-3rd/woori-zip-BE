package fisa.woorizip.backend.house;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum HouseErrorCode implements ErrorCode {

    HOUSE_NOT_FOUND(NOT_FOUND, "매물을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    HouseErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }


}
