package fisa.woorizip.backend.loangoods;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;
import fisa.woorizip.backend.loangoods.dto.LoanGoodsResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum LoanGoodsErrorCode implements ErrorCode{
    LOANGOODS_NOT_FOUND(NOT_FOUND, "대출상품이 존재하지 않습니다");

    private final HttpStatus status;
    private final String message;

    LoanGoodsErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}



