package fisa.woorizip.backend.loangoods;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum LoanGoodsErrorCode implements ErrorCode {
    LOAN_GOODS_NOT_FOUND(NOT_FOUND, "대출 상품이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    LoanGoodsErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
