package fisa.woorizip.backend.bookmark.dto.response;

import fisa.woorizip.backend.bookmark.domain.Bookmark;

import lombok.Builder;
import lombok.Getter;

import lombok.ToString;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@Builder
@ToString
public class ShowBookmarksResponse {

    private final boolean hasNext;
    private final List<BookmarkListResponse> bookmarks;

    public static ShowBookmarksResponse from(Slice<Bookmark> bookmarks) {
        return builder()
                .hasNext(bookmarks.hasNext())
                .bookmarks(bookmarks.getContent().stream().map(BookmarkListResponse::from).toList())
                .build();
    }
}
