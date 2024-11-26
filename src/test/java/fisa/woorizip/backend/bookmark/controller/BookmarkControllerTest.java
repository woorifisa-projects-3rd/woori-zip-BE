package fisa.woorizip.backend.bookmark.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.OK;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.support.ControllerTest;
import fisa.woorizip.backend.support.fixture.MemberFixture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookmarkControllerTest extends ControllerTest {

    @Test
    @DisplayName("북마크 추가 성공")
    void 북마크_목록을_추가할_수_있습니다() {
        Member member = MemberFixture.builder().id(2L).build();
        baseRestAssuredWithAuth(member)
                .when()
                .post("/api/v1/houses/1/bookmark")
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }

    @Test
    @DisplayName("북마크 빈목록 조회")
    void 북마크한_집이_없는_경우_빈북마크_목록_반환한다() {
        Member member = MemberFixture.builder().id(2L).build();

        baseRestAssuredWithAuth(member)
                .when()
                .get("/api/v1/bookmarks")
                .then()
                .log()
                .all()
                .statusCode(OK.value())
                .body("bookmarks", empty())
                .body("hasNext", is(false));
    }
}
