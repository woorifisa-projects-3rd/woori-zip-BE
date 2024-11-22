package fisa.woorizip.backend.bookmark.dto.response;

import fisa.woorizip.backend.bookmark.domain.Bookmark;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
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
    private final LocalDateTime createdAt;

    public static List<BookmarkListResponse> from(List<Bookmark> bookmarks) {
        return bookmarks.stream()
                .map(
                        bookmark ->
                                builder()
                                        .bookmarkId(bookmark.getId())
                                        .houseId(bookmark.getHouse().getId())
                                        .houseName(bookmark.getHouse().getName())
                                        .address(bookmark.getHouse().getAddress())
                                        .gu(bookmark.getHouse().getGu())
                                        .dong(bookmark.getHouse().getDong())
                                        .deposit(bookmark.getHouse().getDeposit())
                                        .monthlyRentFee(bookmark.getHouse().getMonthlyRentFee())
                                        .imageUrl(bookmark.getHouse().getRepresentativeImage())
                                        .createdAt(bookmark.getCreatedAt())
                                        .build())
                .toList();
    }
}
