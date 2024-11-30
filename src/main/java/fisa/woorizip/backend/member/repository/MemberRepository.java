package fisa.woorizip.backend.member.repository;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<Member> findByCustomerId(String customerId);

    List<Member> findAllByIdIn(List<Long> ids);

    Page<Member> findAllByRole(Role role, Pageable pageable);
}
