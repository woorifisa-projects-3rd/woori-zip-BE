package fisa.woorizip.backend.loanchecklist;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum LoanChecklistErrorCode implements ErrorCode {
    ;

    private final HttpStatus status;
    private final String message;

    LoanChecklistErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
