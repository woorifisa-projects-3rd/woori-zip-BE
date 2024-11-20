package fisa.woorizip.backend.member.domain;

import static fisa.woorizip.backend.member.MemberErrorCode.ROLE_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {
    MEMBER("회원"),
    AGENT("공인중개사"),
    ADMIN("관리자");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public static Role from(String name) {
        return Arrays.stream(Role.values())
                .filter(role -> role.name().equals(name))
                .findAny()
                .orElseThrow(() -> new WooriZipException(ROLE_NOT_FOUND));
    }

}
