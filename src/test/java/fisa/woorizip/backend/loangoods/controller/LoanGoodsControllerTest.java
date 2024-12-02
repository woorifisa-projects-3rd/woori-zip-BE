package fisa.woorizip.backend.loangoods.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.OK;

import fisa.woorizip.backend.support.ControllerTest;

import org.junit.jupiter.api.Test;

class LoanGoodsControllerTest extends ControllerTest {

    @Test
    void 대출상품의_정보를_조회할_수_있다() {
        Long loanGoodsId = 1L;

        baseRestAssured()
                .get("/api/v1/loans/{loanGoodsId}", loanGoodsId)
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }
}
