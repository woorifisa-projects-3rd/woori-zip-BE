package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.member.domain.Member;

public class BookmarkFixture {
    private Long id;
    private Member member;
    private House house;

    public static BookmarkFixture builder() {
        return new BookmarkFixture();
    }

    public BookmarkFixture id(Long id) {
        this.id = id;
        return this;
    }

    public BookmarkFixture member(Member member) {
        this.member = member;
        return this;
    }

    public BookmarkFixture house(House house) {
        this.house = house;
        return this;
    }

    // 실제 Bookmark 객체를 생성하여 반환
    public Bookmark build() {
        return Bookmark.builder()
                .id(id)
                .member(member)
                .house(house)
                .build();
    }
}
