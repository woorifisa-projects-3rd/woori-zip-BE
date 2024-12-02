package fisa.woorizip.backend.rate.repository;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.rate.domain.Rate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> findByLoanGoodsId(Long loanGoodsId);
}
