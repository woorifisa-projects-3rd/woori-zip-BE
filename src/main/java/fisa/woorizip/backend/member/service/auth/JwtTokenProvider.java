package fisa.woorizip.backend.member.service.auth;

import static fisa.woorizip.backend.member.AuthErrorCode.EXPIRED_TOKEN;
import static fisa.woorizip.backend.member.AuthErrorCode.FAILED_SIGNATURE_TOKEN;
import static fisa.woorizip.backend.member.AuthErrorCode.INCORRECTLY_CONSTRUCTED_TOKEN;
import static fisa.woorizip.backend.member.AuthErrorCode.INCORRECT_CONSTRUCT_HEADER;
import static fisa.woorizip.backend.member.AuthErrorCode.INVALID_CLAIM_TYPE;
import static fisa.woorizip.backend.member.AuthErrorCode.MISSING_ISSUER_TOKEN;
import static fisa.woorizip.backend.member.AuthErrorCode.NOT_WOOHAENGSHI_TOKEN;
import static fisa.woorizip.backend.member.AuthErrorCode.UNSUPPORTED_TOKEN;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.MissingClaimException;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

    private static final String MEMBER_ID = "memberId";
    private static final String MEMBER_ROLE = "memberRole";
    private static final String ISSUER = "woorizip";
    private static final String TOKEN_TYPE = "Bearer";

    private SecretKey key;
    private Long accessExpiration;

    @Autowired
    public JwtTokenProvider(
            @Value("${security.jwt.key}") String key,
            @Value("${security.jwt.expiration.access}") Long accessExpiration) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(key));
        this.accessExpiration = accessExpiration;
    }

    private String createToken(Long memberId, Long expiration, Role role) {
        return Jwts.builder()
                .claims()
                .add(MEMBER_ID, memberId)
                .add(MEMBER_ROLE, role.name())
                .and()
                .signWith(key)
                .issuer(ISSUER)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public String createAccessToken(Member member) {
        return createToken(member.getId(), accessExpiration, member.getRole());
    }

    public void validToken(String token) {
        try {
            getClaimsJwt(token);
        } catch (MissingClaimException e) {
            throw new WooriZipException(MISSING_ISSUER_TOKEN);
        } catch (IncorrectClaimException e) {
            throw new WooriZipException(NOT_WOOHAENGSHI_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new WooriZipException(EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new WooriZipException(UNSUPPORTED_TOKEN);
        } catch (SignatureException e) {
            throw new WooriZipException(FAILED_SIGNATURE_TOKEN);
        } catch (MalformedJwtException e) {
            throw new WooriZipException(INCORRECTLY_CONSTRUCTED_TOKEN);
        }
    }

    private Jws<Claims> getClaimsJwt(String token) {
        return Jwts.parser().verifyWith(key).requireIssuer(ISSUER).build().parseClaimsJws(token);
    }

    public MemberIdentity getMemberIdentity(String token) {
        try {
            Claims payload = getClaimsJwt(token).getPayload();
            return new MemberIdentity(
                    payload.get(MEMBER_ID, Long.class), payload.get(MEMBER_ROLE, String.class));
        } catch (RequiredTypeException e) {
            throw new WooriZipException(INVALID_CLAIM_TYPE);
        }
    }

    public Role getMemberRole(String token) {
        try {
            return Role.from(getClaimsJwt(token).getPayload().get(MEMBER_ROLE, String.class));
        } catch (RequiredTypeException e) {
            throw new WooriZipException(INVALID_CLAIM_TYPE);
        }
    }

    public Long getMemberId(String token) {
        try {
            return getClaimsJwt(token).getPayload().get(MEMBER_ID, Long.class);
        } catch (RequiredTypeException e) {
            throw new WooriZipException(INVALID_CLAIM_TYPE);
        }
    }

    public String extractAccessToken(String authorization) {
        String[] tokenFormat = authorization.split(" ");
        if (tokenFormat.length != 2 && !tokenFormat[0].equals(TOKEN_TYPE)) {
            throw new WooriZipException(INCORRECT_CONSTRUCT_HEADER);
        }
        return tokenFormat[1];
    }
}
