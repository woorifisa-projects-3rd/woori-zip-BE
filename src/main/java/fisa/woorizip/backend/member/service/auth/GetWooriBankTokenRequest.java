package fisa.woorizip.backend.member.service.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetWooriBankTokenRequest {

    private String grantType;
    private String clientId;
    private String redirectUri;
    private String code;
    private String clientSecret;

    @Builder
    private GetWooriBankTokenRequest(
            String grantType,
            String clientId,
            String redirectUri,
            String clientSecret,
            String code) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.code = code;
        this.clientSecret = clientSecret;
    }

    protected GetWooriBankTokenRequest() {}
}
