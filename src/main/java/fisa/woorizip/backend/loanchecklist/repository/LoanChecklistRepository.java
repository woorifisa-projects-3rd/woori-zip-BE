package fisa.woorizip.backend.loanchecklist.repository;

import fisa.woorizip.backend.loanchecklist.domain.LoanChecklist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanChecklistRepository
        extends JpaRepository<LoanChecklist, Long>, LoanCheckListCustomRepository {}
