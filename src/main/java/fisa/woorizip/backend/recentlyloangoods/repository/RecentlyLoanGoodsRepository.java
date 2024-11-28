package fisa.woorizip.backend.recentlyloangoods.repository;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.recentlyloangoods.domain.RecentlyLoanGoods;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecentlyLoanGoodsRepository extends JpaRepository<RecentlyLoanGoods, Long> {

    @Query(
            "SELECT rg.loanGoods FROM RecentlyLoanGoods rg WHERE rg.member.id = :memberId ORDER BY"
                + " rg.lookedAt DESC")
    Slice<LoanGoods> findLoanGoodsByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
