package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;

import java.util.List;

public interface BookmarkService {

    void addBookmark(MemberIdentity memberIdentity, Long houseId);

}
