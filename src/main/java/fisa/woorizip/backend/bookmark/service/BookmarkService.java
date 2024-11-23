package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.bookmark.dto.response.BookmarkSliceResponse;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;

import org.springframework.data.domain.Pageable;

public interface BookmarkService {

    void addBookmark(MemberIdentity memberIdentity, Long houseId);

    BookmarkSliceResponse getBookmarkList(MemberIdentity memberIdentity, Pageable pageable);
}