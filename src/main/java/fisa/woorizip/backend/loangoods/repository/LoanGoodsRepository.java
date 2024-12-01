package fisa.woorizip.backend.loangoods.repository;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanGoodsRepository extends JpaRepository<LoanGoods, Long> {

    Slice<LoanGoods> findAllBy(Pageable pageable);
}
