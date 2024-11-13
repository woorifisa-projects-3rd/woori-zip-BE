package fisa.woorizip.backend.common.exception.errorcode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import org.springframework.http.HttpStatus;

public enum CommonErrorCode implements ErrorCode {
    INVALID_INPUT(BAD_REQUEST, "잘못된 입력 값입니다."),
    RESOURCE_NOT_FOUND(NOT_FOUND, "요청하신 경로 => `[%s] %s` 를 찾을 수 없습니다. 경로가 정확한지 확인해 주세요"),
    HTTP_METHOD_NOT_ALLOWED(
            METHOD_NOT_ALLOWED, "요청 HTTP METHOD는 <%s>이지만, 해당 URI를 지원하는 HTTP METHOD는 <%s>입니다."),
    TYPE_MISMATCH(UNPROCESSABLE_ENTITY, "%s의 입력 값으로 %s는 type이 맞지 않습니다. %s의 type은 %s여야 합니다.");

    private final HttpStatus status;
    private final String message;

    CommonErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
