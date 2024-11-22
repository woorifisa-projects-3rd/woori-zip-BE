package fisa.woorizip.backend.bookmark.controller;

import fisa.woorizip.backend.bookmark.dto.response.BookmarkSliceResponse;
import fisa.woorizip.backend.bookmark.service.BookmarkService;
import static fisa.woorizip.backend.bookmark.BookmarkErrorCode.BOOKMARK_ALREADY_EXIST;

import static org.springframework.http.HttpStatus.OK;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.support.ControllerTest;
import fisa.woorizip.backend.support.fixture.MemberFixture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookmarkControllerTest extends ControllerTest {

    @Mock
    private BookmarkService bookmarkService;

    @InjectMocks
    private BookmarkController bookmarkController;

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

    @Test
    @DisplayName("회원이 북마크한 매물이 없는 경우 빈 목록을 반환한다")
    void getBookmarkList_noBookmarkedHouses() {

        Long memberId = 999L;
        Pageable pageable = PageRequest.of(0, 10);
        BookmarkSliceResponse emptyResponse = BookmarkSliceResponse.builder()
                .bookmarks(Collections.emptyList())
                .hasNext(false)
                .numberOfElements(0)
                .build();

        when(bookmarkService.getBookmarkList(memberId, pageable)).thenReturn(emptyResponse);

        BookmarkSliceResponse response = bookmarkController.getBookmarkList(memberId, pageable);

        assertAll(
                () -> assertThat(response.getBookmarks()).isEmpty(),
                () -> assertThat(response.isHasNext()).isFalse(),
                () -> assertThat(response.getNumberOfElements()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("존재하지 않는 회원의 북마크 목록을 조회할 수 없다")
    void getBookmarkList_nonExistentMember() {

        Long nonExistentMemberId = 999L;
        Pageable pageable = PageRequest.of(0, 10);
        BookmarkSliceResponse emptyResponse = BookmarkSliceResponse.builder()
                .bookmarks(Collections.emptyList())
                .hasNext(false)
                .numberOfElements(0)
                .build();

        when(bookmarkService.getBookmarkList(nonExistentMemberId, pageable)).thenReturn(emptyResponse);

        BookmarkSliceResponse response = bookmarkController.getBookmarkList(nonExistentMemberId, pageable);

        assertAll(
                () -> assertThat(response.getBookmarks()).isEmpty(),
                () -> assertThat(response.isHasNext()).isFalse(),
                () -> assertThat(response.getNumberOfElements()).isEqualTo(0)
        );
    }
}