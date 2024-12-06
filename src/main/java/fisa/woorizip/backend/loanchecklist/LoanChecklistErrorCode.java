package fisa.woorizip.backend.loanchecklist;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum LoanChecklistErrorCode implements ErrorCode {
    LOAN_CHECKLIST_NOT_FOUND(NOT_FOUND, "대출 상품에 해당하는 체크리스트가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    LoanChecklistErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
