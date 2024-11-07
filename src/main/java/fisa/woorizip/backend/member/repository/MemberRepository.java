package fisa.woorizip.backend.member.repository;

import fisa.woorizip.backend.member.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}
