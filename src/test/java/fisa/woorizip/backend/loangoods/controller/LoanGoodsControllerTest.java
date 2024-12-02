package fisa.woorizip.backend.loangoods.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.OK;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.support.ControllerTest;

import fisa.woorizip.backend.support.fixture.MemberFixture;
import org.junit.jupiter.api.Test;

class LoanGoodsControllerTest extends ControllerTest {

    @Test
    void 대출상품의_정보를_조회할_수_있다() {
        Long loanGoodsId = 1L;

        Member member = MemberFixture.builder().id(1L).build();

        baseRestAssuredWithAuth(member)
                .get("/api/v1/loangoods/{loanGoodsId}", loanGoodsId)
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }
}
