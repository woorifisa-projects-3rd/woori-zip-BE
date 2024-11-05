package fisa.woorizip.backend.facility.repository;

import fisa.woorizip.backend.facility.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
