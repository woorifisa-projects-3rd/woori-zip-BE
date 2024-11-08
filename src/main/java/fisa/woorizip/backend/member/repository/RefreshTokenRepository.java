package fisa.woorizip.backend.member.repository;

import fisa.woorizip.backend.member.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByValue(String token);
    void deleteAllByMemberId(Long memberId);
}
