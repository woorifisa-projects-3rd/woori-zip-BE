package fisa.woorizip.backend.member.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.MemberFixture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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

    @Test
    void role과_Pageable로_회원들을_조회할_수_있댜() {
        Member member1 = 저장(MemberFixture.builder().role(Role.ADMIN).build());
        Member member2 = 저장(MemberFixture.builder().role(Role.MEMBER).build());
        Member member3 = 저장(MemberFixture.builder().role(Role.ADMIN).build());

        Page<Member> members = memberRepository.findAllByRole(Role.ADMIN, PageRequest.of(0, 2));
        assertAll(
                () -> assertThat(members.getNumber()).isEqualTo(0),
                () -> assertThat(members.getTotalPages()).isEqualTo(1),
                () -> assertThat(members.getContent()).containsExactly(member1, member3));
    }
}
