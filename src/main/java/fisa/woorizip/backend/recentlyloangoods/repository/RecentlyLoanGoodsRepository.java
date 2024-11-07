package fisa.woorizip.backend.recentlyloangoods.repository;

import fisa.woorizip.backend.recentlyloangoods.domain.RecentlyLoanGoods;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentlyLoanGoodsRepository extends JpaRepository<RecentlyLoanGoods, Long> {}
