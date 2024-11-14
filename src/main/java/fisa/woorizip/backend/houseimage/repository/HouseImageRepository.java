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

    // order_index -> orderIndex로 수정 (엔티티의 필드명과 일치시킴)
    // 지금은 하나만 조회하는 거잖아 그러니까 단일로 조회해도 상관은 없느넫, 우리가 흔히 말하는 확장성
    // 만약 프론트에서 imageUrl Id도 줘여 !
    // 그러면 변경사항이 너무 많아진다는겨
    // DTO로 바로 받는거 , 엔티티를 반환받는게 아니라 필요한 필드만 받아오는게 잘생각해보고 해야된다는거지
    // 필요한 필드만 받는거 : 리소스를 줄일 수 있는 장점, 확장할 때 유지보수가 거지같고, 재사용성이 떨어지는 단점이 있다. !
    @Query("SELECT hi.url FROM HouseImage hi WHERE hi.house.id = :houseId order by hi.orderIndex")
    List<String> findImageUrlsByHouseId(@Param("houseId") Long houseId);
}