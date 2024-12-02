package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.domain.RateType;
import jakarta.persistence.*;

import java.time.LocalDateTime;



public class RecentlyLoanGoodsFixture {

    private Long id;
    private Member member;
    private LoanGoods loanGoods;
    private LocalDateTime lookedAt;


    public static RecentlyLoanGoodsFixture builder() {
        return new RecentlyLoanGoodsFixture();
    }

    public RecentlyLoanGoodsFixture id(Long id) {
        this.id = id;
        return this;
    }

    public RecentlyLoanGoodsFixture member(Member member) {
        this.member = member;
        return this;
    }

    public RecentlyLoanGoodsFixture loanGoods(LoanGoods loanGoods) {
        this.loanGoods = loanGoods;
        return this;
    }

    public RecentlyLoanGoodsFixture lookedAt(LocalDateTime lookedAt) {
        this.lookedAt = lookedAt;
        return this;
    }

    public RecentlyLoanGoodsFixture build() {
        return RecentlyLoanGoodsFixture.builder()
                .id(id)
                .member(member)
                .loanGoods(loanGoods)
                .lookedAt(lookedAt)
                .build();
    }
}
