package fisa.woorizip.backend.memberconsumption.repository;

import fisa.woorizip.backend.memberconsumption.domain.MemberConsumption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberConsumptionRepository extends JpaRepository<MemberConsumption, Long> {}
