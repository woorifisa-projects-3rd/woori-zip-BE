package fisa.woorizip.backend.member.controller.auth;

import static fisa.woorizip.backend.member.controller.auth.CookieProvider.REFRESH_TOKEN;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

import fisa.woorizip.backend.member.dto.request.SignInRequest;
import fisa.woorizip.backend.member.dto.response.SignInResponse;
import fisa.woorizip.backend.member.dto.result.SignInResult;
import fisa.woorizip.backend.member.service.auth.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;
    private final CookieProvider cookieProvider;

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResult signInResult = authService.signIn(signInRequest);
        return ResponseEntity.ok()
                .header(
                        SET_COOKIE,
                        cookieProvider
                                .createRefreshTokenCookie(signInResult.getRefreshTokenResult())
                                .toString())
                .body(signInResult.getSignInResponse());
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(
            @CookieValue(name = REFRESH_TOKEN, required = false) String refreshToken) {
        authService.signOut(refreshToken);
        return ResponseEntity.noContent()
                .header(SET_COOKIE, cookieProvider.createSignOutCookie().toString())
                .build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<SignInResponse> reissue(
            @CookieValue(name = REFRESH_TOKEN, required = false) String refreshToken) {
        SignInResult result = authService.reissue(refreshToken);
        return ResponseEntity.ok()
                .header(SET_COOKIE, cookieProvider.createSignOutCookie().toString())
                .body(result.getSignInResponse());
    }

}
