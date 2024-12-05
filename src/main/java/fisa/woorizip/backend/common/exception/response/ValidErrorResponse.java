package fisa.woorizip.backend.common.exception.response;

import lombok.Getter;

import lombok.ToString;
import org.springframework.validation.FieldError;

@Getter
@ToString
public class ValidErrorResponse {

    private final String field;
    private final String message;

    public static ValidErrorResponse from(FieldError error) {
        return new ValidErrorResponse(error.getField(), error.getDefaultMessage());
    }

    public ValidErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
