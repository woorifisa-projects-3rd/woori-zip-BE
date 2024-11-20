package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.dto.request.BookmarkRequest;

public interface BookmarkService {
    void addBookmark(BookmarkRequest bookmarkRequest);
}
