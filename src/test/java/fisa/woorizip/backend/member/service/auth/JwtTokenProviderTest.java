package fisa.woorizip.backend.member.service.auth;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.support.fixture.MemberFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private static final String key =
            "55e32f10a70c4362a393055896bb17c955e32f10a70c4362a393055896bb17c9";
    private static final Long accessExpiration = 100000L;
    private Member member = MemberFixture.builder().id(1L).build();


    @Test
    void 유효기간이_지난_토큰은_예외를_던진다() {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(key, 0L);
        String accessToken = jwtTokenProvider.createAccessToken(member);
        assertThatThrownBy(() -> jwtTokenProvider.validToken(accessToken))
                .isExactlyInstanceOf(WooriZipException.class);
    }

    @Test
    void 토큰_생성() {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(key, accessExpiration);
        String accessToken = jwtTokenProvider.createAccessToken(member);
        assertThat(accessToken).isNotBlank();
    }

    @Test
    void 토큰의_claim을_가져올_수_있다() {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(key, accessExpiration);
        String accessToken = jwtTokenProvider.createAccessToken(member);
        MemberIdentity memberIdentity = jwtTokenProvider.getMemberIdentity(accessToken);
        assertAll(
                () -> assertThat(memberIdentity.getId()).isEqualTo(member.getId()),
                () -> assertThat(memberIdentity.getRole()).isEqualTo(member.getRole())
        );
    }
}