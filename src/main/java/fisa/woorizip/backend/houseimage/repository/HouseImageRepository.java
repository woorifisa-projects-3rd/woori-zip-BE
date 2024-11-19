package fisa.woorizip.backend.houseimage.repository;

import fisa.woorizip.backend.houseimage.domain.HouseImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HouseImageRepository extends JpaRepository<HouseImage, Long> {

    Optional<HouseImage> findById(Long id);

    Optional<HouseImage> findByHouseId(Long id);

    @Query("SELECT hi.url FROM HouseImage hi WHERE hi.house.id = :houseId order by hi.orderIndex")
    List<String> findImageUrlsByHouseId(@Param("houseId") Long houseId);
}
