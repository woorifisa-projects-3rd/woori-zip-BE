package fisa.woorizip.backend.bookmark;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum BookmarkErrorCode implements ErrorCode {
    BOOKMARK_ALREADY_EXIST(CONFLICT, "이미 존재하는 북마크입니다."),
    BOOKMARK_NOT_FOUND(NOT_FOUND, "북마크가 존재하지 않습니다.");


    private final HttpStatus status;
    private final String message;

    BookmarkErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
