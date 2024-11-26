package fisa.woorizip.backend.bookmark.repository;

import fisa.woorizip.backend.bookmark.domain.Bookmark;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    boolean existsByMemberIdAndHouseId(Long memberId, Long houseId);

    @Query(value = "select b FROM Bookmark b JOIN FETCH b.house h WHERE b.member.id = :memberId ORDER BY b.createdAt DESC")
    Slice<Bookmark> findBookmarksWithHouse(@Param("memberId") Long memberId, Pageable pageable);
}
