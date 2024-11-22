package fisa.woorizip.backend.bookmark.repository;

import fisa.woorizip.backend.bookmark.domain.Bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    boolean existsByMemberIdAndHouseId(Long memberId, Long houseId);

    List<Bookmark> findAllByMemberIdOrderByCreatedAtDesc(Long memberId);
}
