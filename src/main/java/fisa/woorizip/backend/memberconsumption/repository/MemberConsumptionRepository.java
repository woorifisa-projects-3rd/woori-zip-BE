package fisa.woorizip.backend.memberconsumption.repository;

import fisa.woorizip.backend.memberconsumption.domain.MemberConsumption;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberConsumptionRepository extends JpaRepository<MemberConsumption, Long> {

    Optional<MemberConsumption> findByMemberId(Long memberId);
}
