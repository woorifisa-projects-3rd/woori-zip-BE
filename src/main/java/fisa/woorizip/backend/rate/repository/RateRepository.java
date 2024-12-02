package fisa.woorizip.backend.rate.repository;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.rate.domain.Rate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query("SELECT R FROM Rate R JOIN FETCH R.loanGoods WHERE R.loanGoods = :loanGoods")
    List<Rate> findByLoanGoodsIdWithFetchJoin(@Param("loanGoods") LoanGoods loanGoods);
}
