package fisa.woorizip.backend.bookmark.dto.request;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.member.domain.Member;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class BookmarkRequest {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "집 ID는 필수입니다.")
    private Long houseId;

    public BookmarkRequest(Long memberId, Long houseId) {
        this.memberId = memberId;
        this.houseId = houseId;
    }

    protected BookmarkRequest() {}

    public Bookmark toBookmark(Member member, House house) {
        return Bookmark.builder().member(member).house(house).build();
    }
}
