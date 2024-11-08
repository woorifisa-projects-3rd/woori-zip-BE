package fisa.woorizip.backend.support;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.service.auth.JwtTokenProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @LocalServerPort private int port;

    @Autowired private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setPort() {
        RestAssured.port = port;
    }

    public RequestSpecification baseRestAssuredWithAuth(Member member) {
        return RestAssured.given()
                .header(AUTHORIZATION, "Bearer " + jwtTokenProvider.createAccessToken(member))
                .log()
                .all()
                .contentType(ContentType.JSON);
    }

    public RequestSpecification baseRestAssured() {
        return RestAssured.given().log().all().contentType(ContentType.JSON);
    }
}
