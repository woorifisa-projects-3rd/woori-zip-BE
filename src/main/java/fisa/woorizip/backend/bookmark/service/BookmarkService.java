package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.bookmark.dto.response.BookmarkSliceResponse;
import org.springframework.data.domain.Pageable;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;

public interface BookmarkService {

    void addBookmark(MemberIdentity memberIdentity, Long houseId);

    BookmarkSliceResponse getBookmarkList(Long memberId, Pageable pageable);
}
