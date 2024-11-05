package fisa.woorizip.backend.housefacilityrelation.repository;

import fisa.woorizip.backend.housefacilityrelation.domain.HouseFacilityRelation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseFacilityRelationRepository
        extends JpaRepository<HouseFacilityRelation, Long> {}
