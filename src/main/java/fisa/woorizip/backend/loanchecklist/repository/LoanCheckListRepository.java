package fisa.woorizip.backend.loanchecklist.repository;

import fisa.woorizip.backend.loanchecklist.domain.LoanCheckList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanCheckListRepository extends JpaRepository<LoanCheckList, Long> {}
