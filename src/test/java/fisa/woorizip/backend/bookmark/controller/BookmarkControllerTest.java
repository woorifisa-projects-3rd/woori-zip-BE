package fisa.woorizip.backend.bookmark.controller;

import static fisa.woorizip.backend.bookmark.BookmarkErrorCode.BOOKMARK_ALREADY_EXIST;

import static org.springframework.http.HttpStatus.OK;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.support.ControllerTest;
import fisa.woorizip.backend.support.fixture.MemberFixture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BookmarkControllerTest extends ControllerTest {

    @Test
    @DisplayName("북마크 추가 성공")
    void 북마크_목록을_추가할_수_있습니다() {
        Member member = MemberFixture.builder().id(3L).build();

        baseRestAssuredWithAuth(member)
                .when()
                .post("/api/v1/houses/1/bookmark")
                .then()
                .log()
                .all()
                .statusCode(OK.value());
    }

    @Test
    @DisplayName("북마크 추가 실패")
    void 이미_존재하는_북마크입니다() {
        Member member = MemberFixture.builder().id(2L).build();

        baseRestAssuredWithAuth(member)
                .when()
                .post("/api/v1/houses/1/bookmark")
                .then()
                .log()
                .all()
                .statusCode(BOOKMARK_ALREADY_EXIST.getStatus().value());
    }
}
