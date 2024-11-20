package fisa.woorizip.backend.bookmark.service;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.dto.request.BookmarkRequest;
import fisa.woorizip.backend.bookmark.repository.BookmarkRepository;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static fisa.woorizip.backend.bookmark.BookmarkErrorCode.BOOKMARK_ALREADY_EXIST;
import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final HouseRepository houseRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public void addBookmark(BookmarkRequest bookmarkRequest) {
//        log.info("memberId {} ,houseId {}",memberId,houseId);

        bookmarkIsExist(bookmarkRequest.getMemberId(), bookmarkRequest.getHouseId());

        bookmarkRepository.save(
                bookmarkRequest.toBookmark(findMemberByMemberId(bookmarkRequest.getMemberId()), findHouseByHouseId(bookmarkRequest.getHouseId()))
        );
    }

    private Member findMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new WooriZipException(MEMBER_NOT_FOUND));
    }

    private House findHouseByHouseId(Long houseId) {
        return houseRepository.findById(houseId)
                .orElseThrow(() -> new WooriZipException(HOUSE_NOT_FOUND));
    }


    private void bookmarkIsExist(Long memberId, Long houseId) {
        if (bookmarkRepository.findByMemberIdAndHouseId(memberId, houseId).isPresent())
            throw new WooriZipException(BOOKMARK_ALREADY_EXIST);
    }
}
