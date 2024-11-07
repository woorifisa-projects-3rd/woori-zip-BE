package fisa.woorizip.backend.log.repository;

import fisa.woorizip.backend.log.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {}
