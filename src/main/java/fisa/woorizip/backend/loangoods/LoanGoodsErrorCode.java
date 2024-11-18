package fisa.woorizip.backend.loangoods;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum LoanGoodsErrorCode implements ErrorCode {
    LOAN_GOODS_NOT_FOUND(HttpStatus.NOT_FOUND, "대출상품이 존재하지 않습니다");

    private final HttpStatus status;
    private final String message;

    LoanGoodsErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
