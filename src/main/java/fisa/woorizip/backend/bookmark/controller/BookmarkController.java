package fisa.woorizip.backend.bookmark.controller;

import fisa.woorizip.backend.bookmark.service.BookmarkService;
import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Login
    @PostMapping("/houses/{houseId}/bookmark")
    public ResponseEntity<Void> addBookmark(
            @VerifiedMember MemberIdentity memberIdentity, @PathVariable("houseId") Long houseId) {
        bookmarkService.addBookmark(memberIdentity, houseId);
        return ResponseEntity.ok().build();
    }

    @Login
    @DeleteMapping("/houses/{houseId}/bookmark/{bookmarkId}")
    public ResponseEntity<Void> deleteBookmark(
            @VerifiedMember MemberIdentity memberIdentity,
            @PathVariable("houseId") Long houseId,
            @PathVariable("bookmarkId") Long bookmarkId) {

        bookmarkService.deleteBookmark(memberIdentity, houseId, bookmarkId);
        return ResponseEntity.ok().build();
    }
}
