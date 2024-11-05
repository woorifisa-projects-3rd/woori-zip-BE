package fisa.woorizip.backend.housefacilityrelation;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum HouseFacilityRelationErrorCode implements ErrorCode {
    ;

    private final HttpStatus status;
    private final String message;

    HouseFacilityRelationErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
