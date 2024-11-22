package fisa.woorizip.backend.member;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum MemberErrorCode implements ErrorCode {
    EARNINGS_TYPE_NOT_FOUND(NOT_FOUND, "소득 유형이 존재하지 않습니다."),
    MEMBER_NOT_FOUND(NOT_FOUND, "회원을 찾을 수 없습니다."),
    ALREADY_EXIST_USERNAME(CONFLICT, "이미 존재하는 아이디입니다."),
    ROLE_NOT_FOUND(NOT_FOUND, "권한(role)을 찾을 수 없습니다."),
    GENDER_NOT_FOUND(NOT_FOUND, "성별이 존재하지 않습니다."),
    MEMBERSHIP_NOT_FOUND(NOT_FOUND, "회원 등급이 존재하지 않습니다."),
    LIFE_STAGE_NOT_FOUND(NOT_FOUND, "라이프 스테이지가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    MemberErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
