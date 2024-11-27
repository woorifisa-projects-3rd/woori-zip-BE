package fisa.woorizip.backend.bookmark.repository;

import fisa.woorizip.backend.bookmark.domain.Bookmark;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    boolean existsByMemberIdAndHouseId(Long memberId, Long houseId);

    @Query(
            "select b from Bookmark b join fetch b.house h where b.member.id = :memberId order by"
                    + " b.createdAt desc")
    Slice<Bookmark> findBookmarksWithHouse(@Param("memberId") Long memberId, Pageable pageable);

    Optional<Bookmark> findByMemberIdAndHouseId(Long memberId, Long houseId);
}
