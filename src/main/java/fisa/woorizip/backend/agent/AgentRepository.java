package fisa.woorizip.backend.agent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    boolean existsByNameAndLicenseId(String name, String licenseId);
}
