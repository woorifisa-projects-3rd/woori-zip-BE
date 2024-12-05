package fisa.woorizip.backend.member.service.auth;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WooriBankOauth {

    private final String tokenUri;
    private final String memberDataUri;
    private final String grantType;
    private final String clientId;
    private final String redirectUri;
    private final String clientSecret;

    private WebClient webClient = WebClient.builder().build();

    public WooriBankOauth(
            @Value("${woori-bank.token-uri}") String tokenUri,
            @Value("${woori-bank.member-data-uri}") String memberDataUri,
            @Value("${woori-bank.grant-type}") String grantType,
            @Value("${woori-bank.client-id}") String clientId,
            @Value("${woori-bank.redirect-uri}") String redirectUri,
            @Value("${woori-bank.client-secret}") String clientSecret) {
        this.tokenUri = tokenUri;
        this.memberDataUri = memberDataUri;
        this.grantType = grantType;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.clientSecret = clientSecret;
    }

    public GetMemberDataResponse getMemberData(String accessToken) {
        return webClient
                .get()
                .uri(memberDataUri)
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(GetMemberDataResponse.class)
                .block();
    }

    public GetWooriBankTokenResponse getToken(String code) {
        return webClient
                .post()
                .uri(tokenUri)
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(createTokenRequest(code))
                .retrieve()
                .bodyToMono(GetWooriBankTokenResponse.class)
                .block();
    }

    private GetWooriBankTokenRequest createTokenRequest(String code) {
        return GetWooriBankTokenRequest.builder()
                .grantType(grantType)
                .clientId(clientId)
                .redirectUri(redirectUri)
                .clientSecret(clientSecret)
                .code(code)
                .build();
    }
}
