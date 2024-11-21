package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.bookmark.dto.response.BookmarkSliceResponse;
import org.springframework.data.domain.Pageable;

public interface BookmarkService {
    BookmarkSliceResponse getBookmarkList(Long memberId, Pageable pageable);
}