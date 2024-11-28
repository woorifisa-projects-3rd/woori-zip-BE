package fisa.woorizip.backend.rate.repository;

import fisa.woorizip.backend.rate.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {}
