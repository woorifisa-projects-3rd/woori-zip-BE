package fisa.woorizip.backend.bookmark.controller;

import fisa.woorizip.backend.bookmark.dto.response.BookmarkSliceResponse;
import fisa.woorizip.backend.bookmark.service.BookmarkService;
import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Login
    @GetMapping("/bookmarks")
    public BookmarkSliceResponse getBookmarkList(
            @VerifiedMember MemberIdentity memberIdentity,
            @PageableDefault(size = 6) Pageable pageable) {
        return bookmarkService.getBookmarkList(memberIdentity, pageable);
    }

    @Login
    @PostMapping("/houses/{houseId}/bookmark")
    public ResponseEntity<Void> addBookmark(
            @VerifiedMember MemberIdentity memberIdentity, @PathVariable("houseId") Long houseId) {
        bookmarkService.addBookmark(memberIdentity, houseId);
        return ResponseEntity.ok().build();
    }
}
