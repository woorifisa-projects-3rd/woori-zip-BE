package fisa.woorizip.backend.loangoods.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoanGoodsControllerTest {

    @LocalServerPort private int port;

    @BeforeEach
    void setPort() {
        RestAssured.port = port;
    }

    @Test
    void 대출상품의_상세정보_조회() {

        //given
        Long loanGoodsId = 1L;

        //when
        RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/loans/{loansId}", loanGoodsId)
                .then()
                .log().all()
                .statusCode(200)
                .body("id",equalTo(loanGoodsId.intValue()))
                .body("name",not(emptyOrNullString()))
                .body("description",not(emptyOrNullString()))
                .body("content",not(emptyOrNullString()))
                .body("imageUrl",not(emptyOrNullString()))
                .body("loanGoodsType",equalTo("JEONSE"));
      }
}