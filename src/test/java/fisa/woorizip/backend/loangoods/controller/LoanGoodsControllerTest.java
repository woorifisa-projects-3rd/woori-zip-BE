package fisa.woorizip.backend.loangoods.controller;

import static org.springframework.http.HttpStatus.*;

import fisa.woorizip.backend.support.ControllerTest;
import org.junit.jupiter.api.Test;

class LoanGoodsControllerTest extends ControllerTest {

    @Test
    void 대출상품의_상세정보_조회() {

        // given
        Long loanGoodsId = 1L;

        // when
        baseRestAssured()
                .when()
                .get("/api/v1/loans/{loanGoodsId}", loanGoodsId)
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }
}
