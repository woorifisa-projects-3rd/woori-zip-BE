package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import org.springframework.transaction.annotation.Transactional;

public interface BookmarkService {

    void addBookmark(MemberIdentity memberIdentity, Long houseId);

    void deleteBookmark(MemberIdentity memberIdentity, Long bookmarkId, Long houseId);
}
