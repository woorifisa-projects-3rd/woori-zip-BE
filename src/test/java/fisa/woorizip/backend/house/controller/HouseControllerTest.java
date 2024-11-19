package fisa.woorizip.backend.house.controller;

import static fisa.woorizip.backend.common.exception.errorcode.CommonErrorCode.INVALID_INPUT;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import fisa.woorizip.backend.support.ControllerTest;

import org.junit.jupiter.api.Test;

public class HouseControllerTest extends ControllerTest {
    static final double SOUTH_WEST_LATITUDE = 37.452655162589174;
    static final double SOUTH_WEST_LONGITUDE = 126.79611509567208;
    static final double NORTH_EAST_LATITUDE = 37.654612635772615;
    static final double NORTH_EAST_LONGITUDE = 127.09945286237564;

    @Test
    void 줌_레벨이_1_이상인_경우_집을_조회할_수_있다() {
        baseRestAssured()
                .queryParam("level", 1)
                .queryParam("southWestLatitude", SOUTH_WEST_LATITUDE)
                .queryParam("southWestLongitude", SOUTH_WEST_LONGITUDE)
                .queryParam("northEastLatitude", NORTH_EAST_LATITUDE)
                .queryParam("northEastLongitude", NORTH_EAST_LONGITUDE)
                .when()
                .get("/api/v1/houses")
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }

    @Test
    void 줌_레벨이_14_이하인_경우_집을_조회할_수_있다() {
        baseRestAssured()
                .queryParam("level", 14)
                .queryParam("southWestLatitude", SOUTH_WEST_LATITUDE)
                .queryParam("southWestLongitude", SOUTH_WEST_LONGITUDE)
                .queryParam("northEastLatitude", NORTH_EAST_LATITUDE)
                .queryParam("northEastLongitude", NORTH_EAST_LONGITUDE)
                .when()
                .get("/api/v1/houses")
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }

    @Test
    void 줌_레벨이_1_미만인_경우_집을_조회할_수_없다() {
        baseRestAssured()
                .queryParam("level", 0)
                .queryParam("southWestLatitude", SOUTH_WEST_LATITUDE)
                .queryParam("southWestLongitude", SOUTH_WEST_LONGITUDE)
                .queryParam("northEastLatitude", NORTH_EAST_LATITUDE)
                .queryParam("northEastLongitude", NORTH_EAST_LONGITUDE)
                .when()
                .get("/api/v1/houses")
                .then()
                .log()
                .all()
                .statusCode(INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void 줌_레벨이_14_초과인_경우_집을_조회할_수_없다() {
        baseRestAssured()
                .queryParam("level", 15)
                .queryParam("southWestLatitude", SOUTH_WEST_LATITUDE)
                .queryParam("southWestLongitude", SOUTH_WEST_LONGITUDE)
                .queryParam("northEastLatitude", NORTH_EAST_LATITUDE)
                .queryParam("northEastLongitude", NORTH_EAST_LONGITUDE)
                .when()
                .get("/api/v1/houses")
                .then()
                .log()
                .all()
                .statusCode(INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void 남서_위도와_경도_및_북동_위도와_경도_중_1개라도_존재하지_않으면_집을_조회할_수_없다() {
        baseRestAssured()
                .queryParam("level", 9)
                .queryParam("southWestLongitude", SOUTH_WEST_LONGITUDE)
                .queryParam("northEastLatitude", NORTH_EAST_LATITUDE)
                .queryParam("northEastLongitude", NORTH_EAST_LONGITUDE)
                .when()
                .get("/api/v1/houses")
                .then()
                .log()
                .all()
                .statusCode(INVALID_INPUT.getStatus().value());
    }

    @Test
    void 카테고리와_해당_옵션이_있는_경우_집을_조회할_수_있다() {
        baseRestAssured()
                .queryParam("level", 6)
                .queryParam("southWestLatitude", SOUTH_WEST_LATITUDE)
                .queryParam("southWestLongitude", SOUTH_WEST_LONGITUDE)
                .queryParam("northEastLatitude", NORTH_EAST_LATITUDE)
                .queryParam("northEastLongitude", NORTH_EAST_LONGITUDE)
                .queryParam("category", "요식업")
                .queryParam("walking", 10)
                .queryParam("facilityCount", 1)
                .when()
                .get("/api/v1/houses")
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }

    @Test
    void 카테고리가_있는_경우_해당_옵션이_존재하지_않으면_집을_조회할_수_없다() {
        baseRestAssured()
                .queryParam("level", 6)
                .queryParam("southWestLatitude", SOUTH_WEST_LATITUDE)
                .queryParam("southWestLongitude", SOUTH_WEST_LONGITUDE)
                .queryParam("northEastLatitude", NORTH_EAST_LATITUDE)
                .queryParam("northEastLongitude", NORTH_EAST_LONGITUDE)
                .queryParam("category", "요식업")
                .when()
                .get("/api/v1/houses")
                .then()
                .log()
                .all()
                .statusCode(INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void 구와_동에_따라_집을_조회할_수_있다() {
        baseRestAssured()
                .queryParam("level", 6)
                .queryParam("southWestLatitude", SOUTH_WEST_LATITUDE)
                .queryParam("southWestLongitude", SOUTH_WEST_LONGITUDE)
                .queryParam("northEastLatitude", NORTH_EAST_LATITUDE)
                .queryParam("northEastLongitude", NORTH_EAST_LONGITUDE)
                .queryParam("category", "요식업")
                .queryParam("walking", 10)
                .queryParam("facilityCount", 1)
                .queryParam("gu", "강동구")
                .queryParam("dong", "천호동")
                .when()
                .get("/api/v1/houses")
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }
}
