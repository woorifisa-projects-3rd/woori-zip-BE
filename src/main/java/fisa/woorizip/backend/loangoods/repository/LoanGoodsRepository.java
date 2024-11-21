package fisa.woorizip.backend.loangoods.repository;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanGoodsRepository
        extends JpaRepository<LoanGoods, Long>, LoanGoodsRepositoryCustom {

    Optional<LoanGoods> findLoanGoodsById(Long longGoodsId);
}
