package fisa.woorizip.backend.member;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
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
    LIFE_STAGE_NOT_FOUND(NOT_FOUND, "라이프 스테이지가 존재하지 않습니다."),
    AGE_NOT_FOUND(NOT_FOUND, "나이가 존재하지 않습니다."),
    AGENT_LICENSE_ID_IS_NULL(BAD_REQUEST, "부동산 중개업자는 등록번호가 필수입니다."),
    AGENT_NOT_FOUND(NOT_FOUND, "부동산 중개업 정보를 찾을 수 없습니다."),
    NOT_ALLOWED_SIGN_UP(BAD_REQUEST, "일반 회원 가입은 중개업자와 관리자만 가능합니다."),
    ADMINS_NOT_FOUND(NOT_FOUND, "관리자 [%s]를 찾을 수 없습니다."),
    NOT_ADMINS(BAD_REQUEST, "[%s] 는 관리자가 아닙니다."),
    NOT_APPROVED_ADMINS(BAD_REQUEST, "[%s]는 승인된 관리자가 아닙니다."),
    NOT_PENDING_APPROVAL_ADMINS(BAD_REQUEST, "[%s]는 승인 대기중인 관리자가 아닙니다.");

    private final HttpStatus status;
    private final String message;

    MemberErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
