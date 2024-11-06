package fisa.woorizip.backend.common.exception;

import static fisa.woorizip.backend.common.exception.errorcode.CommonErrorCode.INVALID_INPUT;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;
import fisa.woorizip.backend.common.exception.response.ErrorResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                                exception.getBindingResult().getFieldErrors(), INVALID_INPUT));
    }

    private List<String> ENUM_CLASSES =
            List.of("Category", "HouseType", "HousingExpenses", "LoanGoodsType");
    private String TYPE_MISMATCH_MESSAGE = "%s의 입력 값으로 %s는 type이 맞지 않습니다. %s의 type은 %s여야 합니다.";

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(
            MethodArgumentTypeMismatchException exception) {
        String requiredType = getRequiredType(exception);
        String propertyName = exception.getPropertyName();
        Object value = exception.getValue();
        String message =
                String.format(
                        TYPE_MISMATCH_MESSAGE, propertyName, value, propertyName, requiredType);
        return ResponseEntity.status(UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse(BAD_REQUEST.value(), message, UNPROCESSABLE_ENTITY.name()));
    }

    private String getRequiredType(MethodArgumentTypeMismatchException exception) {
        String requiredType = exception.getRequiredType().getSimpleName();
        if (!ENUM_CLASSES.contains(requiredType)) {
            return requiredType;
        }
        return Arrays.stream(exception.getRequiredType().getFields())
                .map(Field::getName)
                .collect(Collectors.joining(", "));
    }

    private static final String METHOD_NOT_SUPPORTED_FORMAT =
            "요청 HTTP METHOD는 <%s>이지만, 해당 URI를 지원하는 HTTP METHOD는 <%s>입니다.";

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleNotSupportedHttpMethodException(
            HttpRequestMethodNotSupportedException exception) {
        String supportedMethods = String.join(", ", exception.getSupportedMethods());
        String message =
                String.format(METHOD_NOT_SUPPORTED_FORMAT, exception.getMethod(), supportedMethods);
        return ResponseEntity.status(METHOD_NOT_ALLOWED)
                .body(
                        new ErrorResponse(
                                METHOD_NOT_ALLOWED.value(), message, METHOD_NOT_ALLOWED.name()));
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
}