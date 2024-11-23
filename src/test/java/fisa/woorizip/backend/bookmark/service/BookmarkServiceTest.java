package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.bookmark.BookmarkErrorCode;
import fisa.woorizip.backend.bookmark.dto.response.BookmarkSliceResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.repository.BookmarkRepository;
import fisa.woorizip.backend.house.domain.House;
import org.junit.jupiter.api.DisplayName;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.support.fixture.HouseFixture;
import fisa.woorizip.backend.support.fixture.MemberFixture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;

import java.util.Optional;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookmarkServiceTest {
    @Mock
    private BookmarkRepository bookmarkRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private HouseRepository houseRepository;

    @InjectMocks
    private BookmarkServiceImpl bookmarkService;

    @Test
    void 회원ID와_집ID를_통해_북마크를_추가할_수_있다() {
        Member member = MemberFixture.builder().id(1L).build();
        House house = HouseFixture.builder().id(1L).build();
        MemberIdentity memberIdentity = new MemberIdentity(member.getId(), member.getRole().toString());

        given(bookmarkRepository.existsByMemberIdAndHouseId(any(Long.class), any(Long.class)))
                .willReturn(false);
        given(memberRepository.findById(any(Long.class))).willReturn(Optional.of(member));
        given(houseRepository.findById(any(Long.class))).willReturn(Optional.of(house));

        bookmarkService.addBookmark(memberIdentity, house.getId());

        assertAll(
                () -> verify(bookmarkRepository, times(1))
                        .existsByMemberIdAndHouseId(member.getId(), house.getId()),
                () -> verify(memberRepository, times(1)).findById(member.getId()),
                () -> verify(houseRepository, times(1)).findById(house.getId()),
                () -> verify(bookmarkRepository, times(1)).save(any(Bookmark.class)));
    }

    @Test
    @DisplayName("북마크가 있는 경우 정상적으로 응답을 반환한다")
    void getBookmarkList_success() {
        Member member = MemberFixture.builder().id(1L).build();
        MemberIdentity memberIdentity = new MemberIdentity(member.getId(), member.getRole().toString());
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

        when(bookmarkRepository.findBookmarksWithHouse(memberIdentity.getId(), pageable)).thenReturn(bookmarkSlice);

        BookmarkSliceResponse response = bookmarkService.getBookmarkList(memberIdentity, pageable);

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
        Member member = MemberFixture.builder().id(2L).build();
        MemberIdentity memberIdentity = new MemberIdentity(member.getId(), member.getRole().toString());
        Pageable pageable = PageRequest.of(0, 5);
        SliceImpl<Bookmark> emptyBookmarkSlice = new SliceImpl<>(Collections.emptyList(), pageable, false);

        when(bookmarkRepository.findBookmarksWithHouse(memberIdentity.getId(), pageable))
                .thenReturn(emptyBookmarkSlice);

        assertAll(
                () -> assertThatThrownBy(() -> bookmarkService.getBookmarkList(memberIdentity, pageable))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(BookmarkErrorCode.BOOKMARK_NOT_FOUND.getMessage())
        );
    }
}