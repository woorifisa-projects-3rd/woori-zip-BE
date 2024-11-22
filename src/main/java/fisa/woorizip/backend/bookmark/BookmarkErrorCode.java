package fisa.woorizip.backend.bookmark;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BookmarkErrorCode {
    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, "북마크가 존재하지 않습니다."),
    BOOKMARK_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 북마크된 집입니다.");

    private final HttpStatus status;
    private final String message;

    BookmarkErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}