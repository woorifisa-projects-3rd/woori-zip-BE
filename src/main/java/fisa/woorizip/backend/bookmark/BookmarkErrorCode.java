package fisa.woorizip.backend.bookmark;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum BookmarkErrorCode implements ErrorCode {
    BOOKMARK_ALREADY_EXIST(CONFLICT, "이미 존재하는 북마크입니다."),
    BOOKMARK_NOT_FOUND(NOT_FOUND, "북마크를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    BookmarkErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
