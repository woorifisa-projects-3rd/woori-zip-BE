package fisa.woorizip.backend.loanchecklist.controller;

import static fisa.woorizip.backend.loangoods.domain.LoanGoodsType.JEONSE;

import static org.springframework.http.HttpStatus.*;

import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;
import fisa.woorizip.backend.support.ControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class LoanCheckListControllerTest extends ControllerTest {

    @Test
    void 대출종류에_따른_체크리스트_조회() {

        LoanGoodsType loanGoodsType = JEONSE;

        baseRestAssured()
                .when()
                .get("/api/v1/loanchecklists?type=JEONSE")
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }
}
