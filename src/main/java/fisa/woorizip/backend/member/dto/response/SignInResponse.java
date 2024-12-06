package fisa.woorizip.backend.member.dto.response;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignInResponse {

    private String accessToken;
    private String name;
    private Role role;

    @Builder
    private SignInResponse(String accessToken, String name, Role role) {
        this.accessToken = accessToken;
        this.name = name;
        this.role = role;
    }

    public static SignInResponse of(String accessToken, Member member) {
        return SignInResponse.builder()
                .accessToken(accessToken)
                .name(member.getName())
                .role(member.getRole())
                .build();
    }
}
