package fisa.woorizip.backend.bookmark.service;

import static fisa.woorizip.backend.bookmark.BookmarkErrorCode.*;
import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.repository.BookmarkRepository;
import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final HouseRepository houseRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void addBookmark(MemberIdentity memberIdentity, Long houseId) {
        validateAlreadyExistBookmark(memberIdentity.getId(), houseId);
        Member member = findMemberByMemberId(memberIdentity.getId());
        House house = findHouseByHouseId(houseId);
        bookmarkRepository.save(createBookmark(member, house));
    }

    private Bookmark createBookmark(Member member, House house) {
        return Bookmark.builder().member(member).house(house).build();
    }

    private Member findMemberByMemberId(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new WooriZipException(MEMBER_NOT_FOUND));
    }

    private House findHouseByHouseId(Long houseId) {
        return houseRepository
                .findById(houseId)
                .orElseThrow(() -> new WooriZipException(HOUSE_NOT_FOUND));
    }

    private void validateAlreadyExistBookmark(Long memberId, Long houseId) {
        if (bookmarkRepository.existsByMemberIdAndHouseId(memberId, houseId))
            throw new WooriZipException(BOOKMARK_ALREADY_EXIST);
    }

    @Override
    @Transactional
    public void deleteBookmark(MemberIdentity memberIdentity, Long houseId) {
        Long memberId = memberIdentity.getId();
        Bookmark bookmark = findBookmarkOrThrow(memberId, houseId);
        bookmarkRepository.delete(bookmark);
    }

    private Bookmark findBookmarkOrThrow(Long memberId, Long houseId) {
        return getBookmark(memberId, houseId)
                .orElseThrow(() -> new WooriZipException(BOOKMARK_NOT_FOUND));
    }

    private Optional<Bookmark> getBookmark(Long memberId, Long houseId) {
        return bookmarkRepository.findByMemberIdAndHouseId(memberId, houseId);
    }

}
