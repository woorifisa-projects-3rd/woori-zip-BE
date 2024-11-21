package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.dto.response.BookmarkSliceResponse;
import fisa.woorizip.backend.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository; // 추가

    @Override
    @Transactional(readOnly = true)
    public BookmarkSliceResponse getBookmarkList(Long memberId, Pageable pageable) {
        Slice<Bookmark> bookmarkSlice = bookmarkRepository.findBookmarksWithHouse(memberId, pageable);
        return BookmarkSliceResponse.from(bookmarkSlice);
    }
}