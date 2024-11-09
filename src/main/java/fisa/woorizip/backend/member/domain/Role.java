package fisa.woorizip.backend.member.domain;

import lombok.Getter;

@Getter
public enum Role {
    MEMBER("회원"),
    AGENT("공인중개사"),
    ADMIN("관리자");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}
