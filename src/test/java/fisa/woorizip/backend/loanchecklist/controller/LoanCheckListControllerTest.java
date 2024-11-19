package fisa.woorizip.backend.loanchecklist.controller;

import static org.springframework.http.HttpStatus.*;

import fisa.woorizip.backend.support.ControllerTest;

import org.junit.jupiter.api.Test;

class LoanCheckListControllerTest extends ControllerTest {

    @Test
    void 대출종류에_따른_체크리스트_조회() {

        baseRestAssured()
                .when()
                .get("/api/v1/loanchecklists?loanGoodsType=JEONSE")
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }
}