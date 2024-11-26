package fisa.woorizip.backend.bookmark.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.dto.response.ShowBookmarksResponse;
import fisa.woorizip.backend.bookmark.repository.BookmarkRepository;
import fisa.woorizip.backend.house.domain.House;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;

import java.util.List;
import java.util.Optional;

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
        MemberIdentity memberIdentity =
                new MemberIdentity(member.getId(), member.getRole().toString());

        given(bookmarkRepository.existsByMemberIdAndHouseId(any(Long.class), any(Long.class)))
                .willReturn(false);
        given(memberRepository.findById(any(Long.class))).willReturn(Optional.of(member));
        given(houseRepository.findById(any(Long.class))).willReturn(Optional.of(house));

        bookmarkService.addBookmark(memberIdentity, house.getId());

        assertAll(
                () ->
                        verify(bookmarkRepository, times(1))
                                .existsByMemberIdAndHouseId(member.getId(), house.getId()),
                () -> verify(memberRepository, times(1)).findById(member.getId()),
                () -> verify(houseRepository, times(1)).findById(house.getId()),
                () -> verify(bookmarkRepository, times(1)).save(any(Bookmark.class)));
    }


    @Test
    void 북마크_목록_조회_성공() {
        Member member = MemberFixture.builder().build();
        MemberIdentity memberIdentity = new MemberIdentity(
                member.getId(),
                member.getRole().toString()
        );
        House house = House.builder()
                .id(1L)
                .name("Test House")
                .build();
        Bookmark bookmark = Bookmark.builder()
                .id(1L)
                .house(house)
                .build();
        List<Bookmark> bookmarks = List.of(bookmark);
        Pageable pageable = Pageable.ofSize(5);
        SliceImpl<Bookmark> expectedBookmarks = new SliceImpl<>(bookmarks, pageable, false);

        given(bookmarkRepository.findBookmarksWithHouse(memberIdentity.getId(), pageable))
                .willReturn(expectedBookmarks);

        ShowBookmarksResponse response = bookmarkService.getBookmarkList(memberIdentity, pageable);

        assertAll(
                () -> verify(bookmarkRepository, times(1)).findBookmarksWithHouse(memberIdentity.getId(), pageable),
                () -> assertThat(response.getBookmarks()).hasSize(1),
                () -> assertThat(response.getBookmarks().get(0).getBookmarkId()).isEqualTo(1L),
                () -> assertThat(response.getBookmarks().get(0).getHouseId()).isEqualTo(1L),
                () -> assertThat(response.getBookmarks().get(0).getHouseName()).isEqualTo("Test House"),
                () -> assertThat(response.isHasNext()).isFalse()
        );
    }

}

