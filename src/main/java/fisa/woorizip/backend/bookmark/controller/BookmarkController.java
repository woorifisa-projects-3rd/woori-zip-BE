package fisa.woorizip.backend.bookmark.controller;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.dto.response.BookmarkResponse;
import fisa.woorizip.backend.bookmark.service.BookmarkService;

import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/houses/{houseId}")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Login
    @PostMapping("/bookmark")
    public ResponseEntity<Void> addBookmark(@VerifiedMember MemberIdentity memberIdentity, @PathVariable("houseId") Long houseId) {
        bookmarkService.addBookmark(memberIdentity, houseId);
        return ResponseEntity.ok().build();
    }

}
