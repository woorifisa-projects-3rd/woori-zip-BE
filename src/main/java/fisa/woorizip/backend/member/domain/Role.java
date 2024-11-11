package fisa.woorizip.backend.member.domain;

import static fisa.woorizip.backend.member.MemberErrorCode.ROLE_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;

import lombok.Getter;

import java.util.Arrays;
import java.util.Set;

@Getter
public enum Role {
    MEMBER("회원", Set.of()),
    AGENT("공인중개사", Set.of(MEMBER)),
    ADMIN("관리자", Set.of(MEMBER, AGENT));

    private final String name;
    private final Set<Role> suRoles;

    Role(String name, Set<Role> suRoles) {
        this.name = name;
        this.suRoles = suRoles;
    }

    public static Role from(String name) {
        return Arrays.stream(Role.values())
                .filter(role -> role.name().equals(name))
                .findAny()
                .orElseThrow(() -> new WooriZipException(ROLE_NOT_FOUND));
    }

    public boolean canAccess(Role requiredRole) {
        return requiredRole == this || this.getSuRoles().contains(requiredRole);
    }
}
