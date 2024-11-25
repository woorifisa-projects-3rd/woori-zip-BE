package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.member.controller.auth.MemberIdentity;

public interface BookmarkService {
    void addBookmark(MemberIdentity memberIdentity, Long houseId);

    void deleteBookmark(MemberIdentity memberIdentity, Long bookmarkId, Long houseId);
}
