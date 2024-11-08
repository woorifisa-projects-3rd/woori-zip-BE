package fisa.woorizip.backend.member.dto.result;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.RefreshToken;
import fisa.woorizip.backend.member.dto.response.SignInResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResult {

    private SignInResponse signInResponse;
    private RefreshTokenResult refreshTokenResult;

    @Builder
    public SignInResult(SignInResponse signInResponse, RefreshTokenResult refreshTokenResult) {
        this.signInResponse = signInResponse;
        this.refreshTokenResult = refreshTokenResult;
    }

    public static SignInResult of(RefreshToken refreshToken, String accessToken, Member member, Long expirationSeconds) {
        return SignInResult.builder()
                .signInResponse(SignInResponse.of(accessToken, member))
                .refreshTokenResult(RefreshTokenResult.of(refreshToken, expirationSeconds))
                .build();
    }
}
