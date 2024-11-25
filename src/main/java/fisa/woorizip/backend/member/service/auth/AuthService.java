package fisa.woorizip.backend.member.service.auth;

import fisa.woorizip.backend.member.dto.request.SignInRequest;
import fisa.woorizip.backend.member.dto.response.SignInResponse;
import fisa.woorizip.backend.member.dto.result.SignInResult;

public interface AuthService {

    SignInResult signIn(final SignInRequest signInRequest);

    void signOut(String refreshToken);

    SignInResult reissue(String refreshToken);

    SignInResponse oauthLogin(String code);
}
