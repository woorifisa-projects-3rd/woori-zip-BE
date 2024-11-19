package fisa.woorizip.backend.houseimage;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum HouseImageErrorCode implements ErrorCode {
    IMAGE_NOT_FOUND(NOT_FOUND, "이미지가 존재하지 않습니다."),
    INVALID_IMAGE_FORMAT(HttpStatus.BAD_REQUEST, "지원하지 않는 이미지 형식입니다."),
    IMAGE_SIZE_EXCEEDED(HttpStatus.PAYLOAD_TOO_LARGE, "이미지 크기가 제한을 초과했습니다."),
    MAX_IMAGE_COUNT_EXCEEDED(HttpStatus.BAD_REQUEST, "이미지 개수가 제한을 초과했습니다."),
    INVALID_ORDER_INDEX(HttpStatus.BAD_REQUEST, "잘못된 이미지 순서 값입니다."),
    DUPLICATE_ORDER_INDEX(HttpStatus.CONFLICT, "중복된 이미지 순서 값이 존재합니다.");

    private final HttpStatus status;
    private final String message;

    HouseImageErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
