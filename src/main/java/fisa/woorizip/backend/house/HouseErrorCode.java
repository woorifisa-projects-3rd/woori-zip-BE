package fisa.woorizip.backend.house;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum HouseErrorCode implements ErrorCode {
  
    HOUSE_NOT_FOUND(NOT_FOUND, "집이 존재하지 않습니다."),
    HOUSE_ADDRESS_TYPE_NOT_FOUND(NOT_FOUND, "집 주소 형식이 존재하지 않습니다."),
    MAP_LEVEL_NOT_FOUND(NOT_FOUND, "줌 레벨이 존재하지 않습니다."),
    HOUSE_TYPE_NOT_FOUND(NOT_FOUND, "집 종류가 존재하지 않습니다."),
    HOUSING_EXPENSES_NOT_FOUND(NOT_FOUND, "주거 비용 타입이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    HouseErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
