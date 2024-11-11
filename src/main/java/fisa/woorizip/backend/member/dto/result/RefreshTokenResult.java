package fisa.woorizip.backend.member.dto.result;

import fisa.woorizip.backend.member.domain.RefreshToken;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RefreshTokenResult {

    private String value;
    private Long expirationSeconds;

    @Builder
    public RefreshTokenResult(String value, Long expirationSeconds) {
        this.value = value;
        this.expirationSeconds = expirationSeconds;
    }

    public static RefreshTokenResult of(RefreshToken refreshToken, Long expirationSeconds) {
        return RefreshTokenResult.builder()
                .value(refreshToken.getValue())
                .expirationSeconds(expirationSeconds)
                .build();
    }
}
