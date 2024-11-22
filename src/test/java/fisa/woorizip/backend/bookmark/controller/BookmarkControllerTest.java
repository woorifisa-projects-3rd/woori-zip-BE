package fisa.woorizip.backend.bookmark.controller;

import fisa.woorizip.backend.bookmark.dto.response.BookmarkSliceResponse;
import fisa.woorizip.backend.bookmark.service.BookmarkService;
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

@DisplayName("북마크 컨트롤러 테스트")
@ExtendWith(MockitoExtension.class)
class BookmarkControllerTest {

    @Mock
    private BookmarkService bookmarkService;

    @InjectMocks
    private BookmarkController bookmarkController;

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