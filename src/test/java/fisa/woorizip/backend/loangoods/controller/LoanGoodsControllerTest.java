package fisa.woorizip.backend.loangoods.controller;

import static org.springframework.http.HttpStatus.*;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.support.ControllerTest;
import fisa.woorizip.backend.support.fixture.MemberFixture;

import org.junit.jupiter.api.Test;

class LoanGoodsControllerTest extends ControllerTest {

    @Test
    void 대출상품의_상세정보_조회() {

        Long loanGoodsId = 1L;

        baseRestAssured()
                .when()
                .get("/api/v1/loans/{loanGoodsId}", loanGoodsId)
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }

    @Test
    void 사용자에_맞는_대출상품을_조회() {

        Member member = MemberFixture.builder().id(1L).build();

        baseRestAssuredWithAuth(member)
                .when()
                .get("/api/v1/loans/recommendation")
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }
}
