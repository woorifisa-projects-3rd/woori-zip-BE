package fisa.woorizip.backend.log.repository;

import fisa.woorizip.backend.log.domain.Log;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.MemberFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
class LogRepositoryTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private LogRepository logRepository;

    private final Member 저장(Member member) {
        return memberRepository.save(member);
    }
    private final Log 저장(Log log) {
        return logRepository.save(log);
    }

    @Test
    void 조건에_맞는_로그를_조회할_수_있다() {
        Member member1 = 저장(MemberFixture.builder().build());
        Member member2 = 저장(MemberFixture.builder().username("최종수").build());
        저장(Log.builder().member(member1).content("Log1").createdAt(LocalDateTime.now().minusHours(2)).build());
        저장(Log.builder().member(member2).content("Log2").createdAt(LocalDateTime.now().minusHours(3)).build());
        저장(Log.builder().member(member2).content("Log3").createdAt(LocalDateTime.now().minusHours(1)).build());
        Log log = 저장(Log.builder().member(member2).content("Log4").createdAt(LocalDateTime.now()).build());

        Page<Log> logs = logRepository.searchLogs(log.getId(), "종수", LocalDateTime.now().minusHours(2), LocalDateTime.now(), PageRequest.of(0, 1));

        assertAll(
                () -> assertThat(logs.getTotalPages()).isEqualTo(1),
                () -> assertThat(logs.getContent()).containsExactly(log)
        );
    }




}