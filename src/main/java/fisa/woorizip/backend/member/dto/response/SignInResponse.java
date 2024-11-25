package fisa.woorizip.backend.member.dto.response;

import fisa.woorizip.backend.member.domain.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResponse {

    private String accessToken;
    private String name;

    @Builder
    private SignInResponse(String accessToken, String name) {
        this.accessToken = accessToken;
        this.name = name;
    }

    public static SignInResponse of(String accessToken, Member member) {
        return SignInResponse.builder().accessToken(accessToken).name(member.getName()).build();
    }

    public static SignInResponse of(String accessToken, String name) {
        return SignInResponse.builder().accessToken(accessToken).name(name).build();
    }
}
