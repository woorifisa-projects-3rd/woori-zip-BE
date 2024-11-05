package fisa.woorizip.backend.bookmark;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BookmarkErrorCode implements ErrorCode {
    ;

    private final HttpStatus status;
    private final String message;

    BookmarkErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
