package fisa.woorizip.backend.bookmark;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import fisa.woorizip.backend.bookmark.service.BookmarkServiceImpl;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.repository.MemberRepository;

import fisa.woorizip.backend.support.fixture.HouseFixture;
import fisa.woorizip.backend.support.fixture.MemberFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.repository.BookmarkRepository;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.member.domain.Member;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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
        Bookmark bookmark = Bookmark.builder()
                .id(1L)
                .member(member)
                .house(house)
                .build();

        MemberIdentity memberIdentity = new MemberIdentity(member.getId(), member.getRole().toString());

        // given
        given(bookmarkRepository.existsByMemberIdAndHouseId(any(Long.class), any(Long.class))).willReturn(false);
        given(memberRepository.findById(any(Long.class))).willReturn(Optional.of(member));
        given(houseRepository.findById(any(Long.class))).willReturn(Optional.of(house));
        given(bookmarkRepository.save(any(Bookmark.class))).willReturn(bookmark);

        // when
        bookmarkService.addBookmark(memberIdentity, house.getId());

        assertAll(
                () -> verify(bookmarkRepository, times(1)).existsByMemberIdAndHouseId(member.getId(), house.getId()),
                () -> verify(memberRepository, times(1)).findById(member.getId()),
                () -> verify(houseRepository, times(1)).findById(house.getId()),
                () -> verify(bookmarkRepository, times(1)).save(bookmark)
        );
    }
}
