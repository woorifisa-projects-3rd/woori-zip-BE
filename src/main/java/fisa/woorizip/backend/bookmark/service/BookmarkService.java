package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.dto.request.BookmarkRequest;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;

public interface BookmarkService {

    void addBookmark(MemberIdentity memberIdentity, Long houseId);
}
