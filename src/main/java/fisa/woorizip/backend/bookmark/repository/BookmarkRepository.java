package fisa.woorizip.backend.bookmark.repository;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query(value = "SELECT b FROM Bookmark b " +
            "JOIN FETCH b.house h " +
            "WHERE b.member.id = :memberId " +
            "ORDER BY b.createdAt DESC",
            countQuery = "SELECT COUNT(b) FROM Bookmark b WHERE b.member.id = :memberId")
    Slice<Bookmark> findBookmarksWithHouse(
            @Param("memberId") Long memberId,
            Pageable pageable);
}