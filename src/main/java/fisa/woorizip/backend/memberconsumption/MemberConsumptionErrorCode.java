package fisa.woorizip.backend.memberconsumption;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum MemberConsumptionErrorCode implements ErrorCode {
    CUSTOMER_TYPE_NOT_FOUND(NOT_FOUND, "고객 유형이 존재하지 않습니다."),
    MEMBER_CONSUMPTION_NOT_FOUND(NOT_FOUND, "소비 내역이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    MemberConsumptionErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
