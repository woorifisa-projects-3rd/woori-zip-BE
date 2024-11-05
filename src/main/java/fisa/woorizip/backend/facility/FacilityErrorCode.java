package fisa.woorizip.backend.facility;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum FacilityErrorCode implements ErrorCode {
    ;

    private final HttpStatus status;
    private final String message;

    FacilityErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
