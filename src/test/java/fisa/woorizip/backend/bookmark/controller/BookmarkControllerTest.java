package fisa.woorizip.backend.bookmark.controller;

import static fisa.woorizip.backend.bookmark.BookmarkErrorCode.BOOKMARK_ALREADY_EXIST;
import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

import fisa.woorizip.backend.bookmark.dto.response.BookmarkSliceResponse;
import fisa.woorizip.backend.bookmark.service.BookmarkService;
import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.support.ControllerTest;
import fisa.woorizip.backend.support.fixture.MemberFixture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class BookmarkControllerTest extends ControllerTest {

    @Mock private BookmarkService bookmarkService;

    @InjectMocks private BookmarkController bookmarkController;

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

        verify(bookmarkService).addBookmark(any(MemberIdentity.class), eq(1L));
    }

    @Test
    @DisplayName("북마크 목록 조회 - 빈 목록")
    void getBookmarkList_noBookmarkedHouses() {

        Member member = MemberFixture.builder().id(999L).build();
        MemberIdentity memberIdentity =
                new MemberIdentity(member.getId(), member.getRole().toString());

        BookmarkSliceResponse emptyResponse =
                BookmarkSliceResponse.builder()
                        .bookmarks(Collections.emptyList())
                        .hasNext(false)
                        .numberOfElements(0)
                        .build();

        when(bookmarkService.getBookmarkList(any(MemberIdentity.class), any(Pageable.class)))
                .thenReturn(emptyResponse);

        baseRestAssuredWithAuth(member)
                .when()
                .get("/api/v1/bookmarks")
                .then()
                .log()
                .all()
                .statusCode(OK.value())
                .body("bookmarks", empty())
                .body("hasNext", is(false))
                .body("numberOfElements", is(0));
    }

    @Test
    @DisplayName("존재하지 않는 회원의 북마크 목록 조회")
    void getBookmarkList_nonExistentMember() {
        // given
        Member member = MemberFixture.builder().id(999L).build();
        MemberIdentity memberIdentity =
                new MemberIdentity(member.getId(), member.getRole().toString());

        when(bookmarkService.getBookmarkList(any(MemberIdentity.class), any(Pageable.class)))
                .thenThrow(new WooriZipException(MEMBER_NOT_FOUND));

        baseRestAssuredWithAuth(member)
                .when()
                .get("/api/v1/bookmarks")
                .then()
                .log()
                .all()
                .statusCode(MEMBER_NOT_FOUND.getStatus().value())
                .body("message", equalTo(MEMBER_NOT_FOUND.getMessage()));
    }

    @Test
    @DisplayName("이미 존재하는 북마크 추가 시도")
    void 이미_존재하는_북마크입니다() {

        Member member = MemberFixture.builder().id(2L).build();
        doThrow(new WooriZipException(BOOKMARK_ALREADY_EXIST))
                .when(bookmarkService)
                .addBookmark(any(MemberIdentity.class), eq(1L));

        baseRestAssuredWithAuth(member)
                .when()
                .post("/api/v1/houses/1/bookmark")
                .then()
                .log()
                .all()
                .statusCode(BOOKMARK_ALREADY_EXIST.getStatus().value())
                .body("message", equalTo(BOOKMARK_ALREADY_EXIST.getMessage()));
    }
}
