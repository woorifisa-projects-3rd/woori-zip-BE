package fisa.woorizip.backend.member.service.auth;

import static fisa.woorizip.backend.member.AuthErrorCode.FAIL_TO_SIGN_IN;
import static fisa.woorizip.backend.member.AuthErrorCode.NOT_YET_ADMIN_APPROVE;
import static fisa.woorizip.backend.member.AuthErrorCode.REFRESH_TOKEN_EXPIRED;
import static fisa.woorizip.backend.member.AuthErrorCode.REFRESH_TOKEN_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.RefreshToken;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.domain.Status;
import fisa.woorizip.backend.member.dto.request.SignInRequest;
import fisa.woorizip.backend.member.dto.result.SignInResult;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.member.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${security.refresh.expiration}")
    private Long expirationSeconds;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final WooriBankOauth wooriBankOauth;

    @Override
    @Transactional
    public SignInResult signIn(SignInRequest request, Role role) {
        final Member member = findMemberByRequest(request);
        if(role == Role.ADMIN) validateAdminStatus(member);
        final String accessToken = jwtTokenProvider.createAccessToken(member);
        refreshTokenRepository.deleteAllByMemberId(member.getId());
        final RefreshToken refreshToken = refreshTokenRepository.save(createRefreshToken(member));
        return SignInResult.of(refreshToken, accessToken, member, expirationSeconds);
    }

    private void validateAdminStatus(Member member) {
        if(member.getStatus() == Status.PENDING_APPROVAL) throw new WooriZipException(NOT_YET_ADMIN_APPROVE);
    }

    @Override
    @Transactional
    public void signOut(String refreshToken) {
        if (!isNull(refreshToken)) {
            refreshTokenRepository.delete(findRefreshToken(refreshToken));
        }
    }

    @Override
    @Transactional
    public SignInResult reissue(String token) {
        validateExistRefreshToken(token);
        RefreshToken refreshToken = findRefreshToken(token);
        validateRefreshTokenExpired(refreshToken);
        refreshToken.reissue(expirationSeconds);
        Member member = refreshToken.getMember();
        String accessToken = jwtTokenProvider.createAccessToken(member);
        return SignInResult.of(refreshToken, accessToken, member, expirationSeconds);
    }

    @Override
    @Transactional
    public SignInResult oauthLogin(String code) {
        GetWooriBankTokenResponse token = wooriBankOauth.getToken(code);
        GetMemberDataResponse memberData = wooriBankOauth.getMemberData(token.getAccessToken());
        Member member = memberRepository.findByCustomerId(memberData.getCustomerId())
                .orElseGet(() -> memberRepository.save(memberData.toMember()));
        String accessToken = jwtTokenProvider.createAccessToken(member);
        refreshTokenRepository.deleteAllByMemberId(member.getId());
        final RefreshToken refreshToken = refreshTokenRepository.save(createRefreshToken(member));
        return SignInResult.of(refreshToken, accessToken, member, expirationSeconds);
    }


    private void validateRefreshTokenExpired(RefreshToken refreshToken) {
        if (refreshToken.isExpired()) throw new WooriZipException(REFRESH_TOKEN_EXPIRED);
    }

    private void validateExistRefreshToken(String refreshToken) {
        if (isNull(refreshToken)) {
            throw new WooriZipException(REFRESH_TOKEN_NOT_FOUND);
        }
    }

    private RefreshToken findRefreshToken(String refreshToken) {
        return refreshTokenRepository
                .findByValue(refreshToken)
                .orElseThrow(() -> new WooriZipException(REFRESH_TOKEN_NOT_FOUND));
    }

    private RefreshToken createRefreshToken(final Member member) {
        return RefreshToken.builder().expirationSeconds(expirationSeconds).member(member).build();
    }

    private Member findMemberByRequest(final SignInRequest signInRequest) {
        final Member member = findMemberByUsername(signInRequest.getUsername());
        validateCorrectPassword(signInRequest.getPassword(), member.getPassword());
        return member;
    }

    private void validateCorrectPassword(final String rawPassword, final String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new WooriZipException(FAIL_TO_SIGN_IN);
        }
    }

    private Member findMemberByUsername(final String username) {
        return memberRepository
                .findByUsername(username)
                .orElseThrow(() -> new WooriZipException(MEMBER_NOT_FOUND));
    }


}
