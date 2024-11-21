package fisa.woorizip.backend.bookmark.dto.response;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@Builder
public class BookmarkSliceResponse {
    private final List<BookmarkListResponse> bookmarks;
    private final boolean hasNext;
    private final int numberOfElements;

    public static BookmarkSliceResponse from(Slice<Bookmark> bookmarkSlice) {
        return builder()
                .bookmarks(BookmarkListResponse.from(bookmarkSlice.getContent()))
                .hasNext(bookmarkSlice.hasNext())
                .numberOfElements(bookmarkSlice.getNumberOfElements())
                .build();
    }
}