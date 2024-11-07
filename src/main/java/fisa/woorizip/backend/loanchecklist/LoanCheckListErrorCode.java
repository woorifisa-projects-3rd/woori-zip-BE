package fisa.woorizip.backend.loanchecklist;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum LoanCheckListErrorCode implements ErrorCode {
    ;

    private final HttpStatus status;
    private final String message;

    LoanCheckListErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
