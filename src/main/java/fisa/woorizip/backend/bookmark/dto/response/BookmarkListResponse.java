package fisa.woorizip.backend.bookmark.dto.response;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.house.domain.House;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BookmarkListResponse {

    private final Long bookmarkId;
    private final Long houseId;
    private final String houseName;
    private final String address;
    private final String gu;
    private final String dong;
    private final long deposit;
    private final long monthlyRentFee;
    private final String imageUrl;

    public static BookmarkListResponse from(Bookmark bookmark) {
        House house = bookmark.getHouse();
        return BookmarkListResponse.builder()
                .bookmarkId(bookmark.getId())
                .houseId(house.getId())
                .houseName(house.getName())
                .address(house.getAddress())
                .gu(house.getGu())
                .dong(house.getDong())
                .deposit(house.getDeposit())
                .monthlyRentFee(house.getMonthlyRentFee())
                .imageUrl(house.getRepresentativeImage())
                .build();
    }
}
