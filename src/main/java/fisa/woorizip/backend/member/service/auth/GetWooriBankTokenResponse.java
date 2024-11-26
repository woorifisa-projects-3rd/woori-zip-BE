package fisa.woorizip.backend.member.service.auth;

import lombok.Getter;

@Getter
public class GetWooriBankTokenResponse {

    private final String accessToken;
    private final String tokenType;

    public GetWooriBankTokenResponse(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }
}
