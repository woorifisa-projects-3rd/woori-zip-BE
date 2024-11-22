package fisa.woorizip.backend.bookmark.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.HouseFixture;
import fisa.woorizip.backend.support.fixture.MemberFixture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.Comparator;

@RepositoryTest
class BookmarkRepositoryTest {

    @Autowired private BookmarkRepository bookmarkRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private HouseRepository houseRepository;

    private Member save(Member member) {
        return memberRepository.save(member);
    }

    private House save(House house) {
        return houseRepository.save(house);
    }

    private Bookmark save(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @Test
    @DisplayName("멤버의 북마크 목록을 최신순(최근에 생성된 순)으로 조회할 수 있다")
    void findBookmarksWithHouse() {

        Member member = save(MemberFixture.builder().build());
        House house1 = save(HouseFixture.builder().member(member).build());
        House house2 = save(HouseFixture.builder().member(member).build());
        Bookmark bookmark1 =
                save(
                        Bookmark.builder()
                                .member(member)
                                .house(house1)
                                .createdAt(LocalDateTime.now().minusHours(2))
                                .build());
        Bookmark bookmark2 =
                save(
                        Bookmark.builder()
                                .member(member)
                                .house(house2)
                                .createdAt(LocalDateTime.now())
                                .build());

        Pageable pageable = PageRequest.of(0, 5);

        Slice<Bookmark> result =
                bookmarkRepository.findBookmarksWithHouse(member.getId(), pageable);

        assertAll(
                () -> assertThat(result.getContent()).hasSize(2),
                () -> assertThat(result.hasNext()).isFalse(),
                () -> assertThat(result.getContent().get(0).getId()).isEqualTo(bookmark2.getId()),
                () -> assertThat(result.getContent().get(1).getId()).isEqualTo(bookmark1.getId()),
                () ->
                        assertThat(result.getContent())
                                .isSortedAccordingTo(
                                        Comparator.comparing(Bookmark::getCreatedAt).reversed()));
    }
}
