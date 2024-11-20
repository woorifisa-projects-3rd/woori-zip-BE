package fisa.woorizip.backend.bookmark.repository;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByMemberIdAndHouseId(Long memberId, Long houseId);

    boolean existsByMemberIdAndHouseId(Long memberId, Long houseId);
}
