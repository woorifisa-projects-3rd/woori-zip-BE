package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.member.domain.Member;

import java.time.LocalDateTime;

public class BookmarkFixture {

    private Long id;

    private Member member;

    private House house;

    private LocalDateTime createdAt = LocalDateTime.now();

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

    public BookmarkFixture createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Bookmark build() {
        return Bookmark.builder().id(id).member(member).house(house).createdAt(createdAt).build();
    }
}
