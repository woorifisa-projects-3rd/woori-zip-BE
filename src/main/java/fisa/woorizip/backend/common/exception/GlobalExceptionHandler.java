package fisa.woorizip.backend.common.exception;

import static fisa.woorizip.backend.common.exception.errorcode.CommonErrorCode.HTTP_METHOD_NOT_ALLOWED;
import static fisa.woorizip.backend.common.exception.errorcode.CommonErrorCode.INVALID_INPUT;
import static fisa.woorizip.backend.common.exception.errorcode.CommonErrorCode.RESOURCE_NOT_FOUND;

import static fisa.woorizip.backend.common.exception.errorcode.CommonErrorCode.TYPE_MISMATCH;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;
import fisa.woorizip.backend.common.exception.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WooriZipException.class)
    public ResponseEntity<ErrorResponse> handleWoohaengshiException(WooriZipException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus()).body(ErrorResponse.from(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidException(
            MethodArgumentNotValidException exception) {
        return ResponseEntity.status(INVALID_INPUT.getStatus())
                .body(
                        ErrorResponse.of(
                                INVALID_INPUT, exception.getBindingResult().getFieldErrors()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(
            MethodArgumentTypeMismatchException exception) {
        String requiredType = getRequiredType(exception);
        String propertyName = exception.getPropertyName();
        Object value = exception.getValue();
        String message =
                String.format(
                        TYPE_MISMATCH.getMessage(), propertyName, value, propertyName, requiredType);
        return ResponseEntity.status(TYPE_MISMATCH.getStatus()).body(ErrorResponse.of(TYPE_MISMATCH, message));
    }

    private String getRequiredType(MethodArgumentTypeMismatchException exception) {
        String requiredType = exception.getRequiredType().getSimpleName();
        if (!EnumScanner.getEnumClasses().contains(requiredType)) {
            return requiredType;
        }
        return Arrays.stream(exception.getRequiredType().getFields())
                .map(Field::getName)
                .collect(Collectors.joining(", "));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleNotSupportedHttpMethodException(
            HttpRequestMethodNotSupportedException exception) {
        String supportedMethods =
                Arrays.stream(exception.getSupportedMethods()).collect(Collectors.joining(", "));
        String message =
                String.format(
                        HTTP_METHOD_NOT_ALLOWED.getMessage(),
                        exception.getMethod(),
                        supportedMethods);
        return ResponseEntity.status(HTTP_METHOD_NOT_ALLOWED.getStatus())
                .body(ErrorResponse.of(HTTP_METHOD_NOT_ALLOWED, message));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        return ResponseEntity.status(INVALID_INPUT.getStatus())
                .body(
                        new ErrorResponse(
                                INVALID_INPUT.getStatus().value(),
                                String.format("잘못된 입력 형식입니다. 에러 메세지 => %s", exception.getMessage()),
                                INVALID_INPUT.name()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(
            NoResourceFoundException exception) {
        String resourcePath = exception.getResourcePath();
        HttpMethod httpMethod = exception.getHttpMethod();
        String message =
                String.format(RESOURCE_NOT_FOUND.getMessage(), httpMethod.name(), resourcePath);
        return ResponseEntity.status(RESOURCE_NOT_FOUND.getStatus())
                .body(ErrorResponse.of(RESOURCE_NOT_FOUND, message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorResponse(
                                INTERNAL_SERVER_ERROR.value(),
                                exception.getMessage(),
                                INTERNAL_SERVER_ERROR.name()));
    }
}
