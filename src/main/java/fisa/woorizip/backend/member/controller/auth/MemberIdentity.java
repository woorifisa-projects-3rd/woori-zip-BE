package fisa.woorizip.backend.member.controller.auth;

import fisa.woorizip.backend.member.domain.Role;
import lombok.Getter;

@Getter
public class MemberIdentity {

    private final Long id;
    private final Role role;

    public MemberIdentity(Long id, String role) {
        this.id = id;
        this.role = Role.from(role);
    }
}
