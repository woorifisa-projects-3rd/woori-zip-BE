package fisa.woorizip.backend.member.domain;

import lombok.Getter;

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

    public boolean canAccess(Role requiredRole) {
        return requiredRole == this || this.getSuRoles().contains(requiredRole);
    }
}
