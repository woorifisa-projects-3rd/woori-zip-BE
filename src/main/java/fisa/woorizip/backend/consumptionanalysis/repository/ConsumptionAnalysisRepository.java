package fisa.woorizip.backend.consumptionanalysis.repository;

import fisa.woorizip.backend.consumptionanalysis.domain.ConsumptionAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumptionAnalysisRepository extends JpaRepository<ConsumptionAnalysis, Long> {

    Optional<ConsumptionAnalysis> findByCustomerType(String customerType);
}
