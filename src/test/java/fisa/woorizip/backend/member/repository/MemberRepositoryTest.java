package fisa.woorizip.backend.member.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.MemberFixture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RepositoryTest
class MemberRepositoryTest {

    @Autowired private MemberRepository memberRepository;

    private final Member 저장(Member member) {
        return memberRepository.save(member);
    }

    @Test
    void username으로_회원을_조회할_수_있다() {
        Member member = 저장(MemberFixture.builder().build());
        Optional<Member> findMember = memberRepository.findByUsername(member.getUsername());
        assertAll(
                () -> assertThat(findMember).isPresent(),
                () -> assertThat(findMember.get().getUsername()).isEqualTo(member.getUsername()));
    }
}
