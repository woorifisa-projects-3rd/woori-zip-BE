package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.bookmark.BookmarkErrorCode;
import fisa.woorizip.backend.bookmark.dto.response.BookmarkSliceResponse;
import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.repository.BookmarkRepository;
import fisa.woorizip.backend.house.domain.House;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookmarkServiceTest {

    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkServiceImpl bookmarkService;

    @Test
    @DisplayName("북마크가 있는 경우 정상적으로 응답을 반환한다")
    void getBookmarkList_success() {

        Long memberId = 1L;
        Pageable pageable = PageRequest.of(0, 5);

        House house = House.builder()
                .id(1L)
                .name("Test House")
                .build();

        Bookmark bookmark = Bookmark.builder()
                .id(1L)
                .house(house)
                .createdAt(null)
                .build();

        SliceImpl<Bookmark> bookmarkSlice = new SliceImpl<>(Collections.singletonList(bookmark), pageable, false);

        when(bookmarkRepository.findBookmarksWithHouse(memberId, pageable)).thenReturn(bookmarkSlice);

        BookmarkSliceResponse response = bookmarkService.getBookmarkList(memberId, pageable);

        assertAll(
                () -> assertThat(response.getBookmarks()).hasSize(1),
                () -> assertThat(response.getBookmarks().get(0).getBookmarkId()).isEqualTo(1L),
                () -> assertThat(response.getBookmarks().get(0).getHouseId()).isEqualTo(1L),
                () -> assertThat(response.getBookmarks().get(0).getHouseName()).isEqualTo("Test House"),
                () -> assertThat(response.isHasNext()).isFalse(),
                () -> assertThat(response.getNumberOfElements()).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("북마크가 없는 경우 예외를 던진다")
    void getBookmarkList_throwsExceptionWhenEmpty() {

        Long memberId = 2L;
        Pageable pageable = PageRequest.of(0, 5);
        SliceImpl<Bookmark> emptyBookmarkSlice = new SliceImpl<>(Collections.emptyList(), pageable, false);

        when(bookmarkRepository.findBookmarksWithHouse(memberId, pageable)).thenReturn(emptyBookmarkSlice);

        assertAll(
                () -> assertThatThrownBy(() -> bookmarkService.getBookmarkList(memberId, pageable))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(BookmarkErrorCode.BOOKMARK_NOT_FOUND.getMessage())
        );
    }
}