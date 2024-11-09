package fisa.woorizip.backend.facility;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum FacilityErrorCode implements ErrorCode {
    CATEGORY_NOT_FOUND(NOT_FOUND, "카테고리가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    FacilityErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
