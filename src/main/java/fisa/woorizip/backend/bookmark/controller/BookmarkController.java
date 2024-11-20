package fisa.woorizip.backend.bookmark.controller;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.dto.request.BookmarkRequest;
import fisa.woorizip.backend.bookmark.service.BookmarkService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark")
    public ResponseEntity<Void> addBookmark(@RequestBody BookmarkRequest request) {
        bookmarkService.addBookmark(request);
        return ResponseEntity.ok().build();
    }

}
