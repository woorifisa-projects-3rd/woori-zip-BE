package fisa.woorizip.backend.bookmark;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum BookmarkErrorCode implements ErrorCode {
    BOOKMARK_NOT_FOUND(NOT_FOUND, "북마크가 존재하지 않습니다."),
    BOOKMARK_ALREADY_EXISTS(CONFLICT, "이미 북마크된 집입니다."),
    BOOKMARK_UNAUTHORIZED(FORBIDDEN, "북마크에 대한 권한이 없습니다."),
    BOOKMARK_INVALID_REQUEST(BAD_REQUEST, "잘못된 북마크 요청입니다.");

    private final HttpStatus status;
    private final String message;

    BookmarkErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }


    }
