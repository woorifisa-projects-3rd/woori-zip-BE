package fisa.woorizip.backend.member.domain;

import lombok.Getter;

@Getter
public enum Status {
    PENDING_APPROVAL("권한 승인 대기"),
    APPROVED("권한 승인"),
    REVOKED_APPROVAL("권한 해제"),
    NOT_ADMIN("관리자 아님");

    private final String description;

    Status(String description) {
        this.description = description;
    }
}
