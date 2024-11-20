package fisa.woorizip.backend.house.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import fisa.woorizip.backend.bookmark.domain.Bookmark;
import fisa.woorizip.backend.bookmark.repository.BookmarkRepository;
import fisa.woorizip.backend.facility.domain.Facility;
import fisa.woorizip.backend.facility.repository.FacilityRepository;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.result.HouseContentResult;
import fisa.woorizip.backend.house.dto.result.HouseCountResult;
import fisa.woorizip.backend.house.dto.result.HouseResult;
import fisa.woorizip.backend.housefacilityrelation.domain.HouseFacilityRelation;
import fisa.woorizip.backend.housefacilityrelation.repository.HouseFacilityRelationRepository;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.FacilityFixture;
import fisa.woorizip.backend.support.fixture.HouseFacilityRelationFixture;
import fisa.woorizip.backend.support.fixture.HouseFixture;
import fisa.woorizip.backend.support.fixture.MemberFixture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RepositoryTest
public class HouseRepositoryTest {
    @Autowired private HouseRepository houseRepository;

    @Autowired private MemberRepository memberRepository;

    @Autowired private FacilityRepository facilityRepository;
    @Autowired private HouseFacilityRelationRepository houseFacilityRelationRepository;
    @Autowired private BookmarkRepository bookmarkRepository;

    static final double SOUTH_WEST_LATITUDE = 37.452655162589174;
    static final double SOUTH_WEST_LONGITUDE = 126.79611509567208;
    static final double NORTH_EAST_LATITUDE = 37.654612635772615;
    static final double NORTH_EAST_LONGITUDE = 127.09945286237564;

    private House save(House house) {
        return houseRepository.save(house);
    }

    private Member save(Member member) {
        return memberRepository.save(member);
    }

    private Facility save(Facility facility) {
        return facilityRepository.save(facility);
    }

    private void save(HouseFacilityRelation houseFacilityRelation) {
        houseFacilityRelationRepository.save(houseFacilityRelation);
    }

    private void save(Bookmark bookmark) {
        bookmarkRepository.save(bookmark);
    }

    @Test
    @DisplayName("매물 ID로 매물을 조회할 수 있다")
    void findById() {
        Member savedMember = memberRepository.save(MemberFixture.builder().build());
        House newHouse = HouseFixture.builder().member(savedMember).build();
        House savedHouse = houseRepository.save(newHouse);

        Optional<House> actualHouse = houseRepository.findById(savedHouse.getId());

        assertThat(actualHouse)
                .isPresent()
                .hasValueSatisfying(
                        foundHouse -> {
                            assertThat(foundHouse.getName()).isEqualTo(newHouse.getName());
                            assertThat(foundHouse.getAddress()).isEqualTo(newHouse.getAddress());
                            assertThat(foundHouse.getHouseType())
                                    .isEqualTo(newHouse.getHouseType());
                            assertThat(foundHouse.getHousingExpenses())
                                    .isEqualTo(newHouse.getHousingExpenses());
                        });
    }

    @Test
    @DisplayName("존재하지 않는 매물 ID로 조회하면 빈 Optional을 반환한다")
    void findById_NotFound() {
        Optional<House> houseLookupResult = houseRepository.findById(999L);

        assertThat(houseLookupResult).isEmpty();
    }

    @Test
    @DisplayName("필터 조건을 만족하는 집의 구 별 개수를 구할 수 있다.")
    public void findHouseCountByGu() {
        Member member = save(MemberFixture.builder().build());
        House house = save(HouseFixture.builder().member(member).build());

        MapFilterRequest request =
                MapFilterRequest.of(
                        9,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE);
        List<HouseCountResult> result = houseRepository.findHouseCountByGu(request);

        assertAll(
                "result",
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).getAddressName()).isEqualTo(house.getGu()),
                () -> assertThat(result.get(0).getCount()).isEqualTo(1));
    }

    @Test
    @DisplayName("필터 조건과 카테고리를 만족하는 집의 구 별 개수를 구할 수 있다.")
    public void findHouseCountByGuInCategory() {
        Member member = save(MemberFixture.builder().build());
        House house = save(HouseFixture.builder().member(member).build());
        Facility facility = save(FacilityFixture.builder().build());
        save(HouseFacilityRelationFixture.builder().house(house).facility(facility).build());

        MapFilterRequest request =
                MapFilterRequest.of(
                        9,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE,
                        facility.getCategory().getName(),
                        10,
                        1);
        List<Long> houseIdList = houseRepository.findHouseIdListByCategory(request);
        List<HouseCountResult> result = houseRepository.findHouseCountByGu(request, houseIdList);

        assertAll(
                "result",
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).getAddressName()).isEqualTo(house.getGu()),
                () -> assertThat(result.get(0).getCount()).isEqualTo(1));
    }

    @Test
    @DisplayName("필터 조건을 만족하는 집의 동 별 개수를 구할 수 있다.")
    public void findHouseCountByDong() {
        Member member = save(MemberFixture.builder().build());
        House house = save(HouseFixture.builder().member(member).build());

        MapFilterRequest request =
                MapFilterRequest.of(
                        6,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE);
        List<HouseCountResult> result = houseRepository.findHouseCountByDong(request);

        assertAll(
                "result",
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).getAddressName()).isEqualTo(house.getDong()),
                () -> assertThat(result.get(0).getCount()).isEqualTo(1));
    }

    @Test
    @DisplayName("필터 조건과 카테고리를 만족하는 집의 동 별 개수를 구할 수 있다.")
    public void findHouseCountByDongInCategory() {
        Member member = save(MemberFixture.builder().build());
        House house = save(HouseFixture.builder().member(member).build());
        Facility facility = save(FacilityFixture.builder().build());
        save(HouseFacilityRelationFixture.builder().house(house).facility(facility).build());

        MapFilterRequest request =
                MapFilterRequest.of(
                        6,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE,
                        facility.getCategory().getName(),
                        10,
                        1);
        List<Long> houseIdList = houseRepository.findHouseIdListByCategory(request);
        List<HouseCountResult> result = houseRepository.findHouseCountByDong(request, houseIdList);

        assertAll(
                "result",
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).getAddressName()).isEqualTo(house.getDong()),
                () -> assertThat(result.get(0).getCount()).isEqualTo(1));
    }

    @Test
    @DisplayName("필터 조건을 만족하는 집의 위도와 경도 목록을 구할 수 있다.")
    public void findHouseLatitudeAndLongitude() {
        Member member = save(MemberFixture.builder().build());
        House house = save(HouseFixture.builder().member(member).build());

        MapFilterRequest request =
                MapFilterRequest.of(
                        1,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE);
        List<HouseResult> result = houseRepository.findHouseLatitudeAndLongitude(request);

        assertAll(
                "result",
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).getLatitude()).isEqualTo(house.getLatitude()),
                () -> assertThat(result.get(0).getLongitude()).isEqualTo(house.getLongitude()));
    }

    @Test
    @DisplayName("필터 조건과 카테고리를 만족하는 집의 위도와 경도 목록을 구할 수 있다.")
    public void findHouseLatitudeAndLongitudeInCategory() {
        Member member = save(MemberFixture.builder().build());
        House house = save(HouseFixture.builder().member(member).build());
        Facility facility = save(FacilityFixture.builder().build());
        save(HouseFacilityRelationFixture.builder().house(house).facility(facility).build());

        MapFilterRequest request =
                MapFilterRequest.of(
                        1,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE,
                        facility.getCategory().getName(),
                        10,
                        1);
        List<Long> houseIdList = houseRepository.findHouseIdListByCategory(request);
        List<HouseResult> result =
                houseRepository.findHouseLatitudeAndLongitude(request, houseIdList);

        assertAll(
                "result",
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).getLatitude()).isEqualTo(house.getLatitude()),
                () -> assertThat(result.get(0).getLongitude()).isEqualTo(house.getLongitude()),
                () -> assertThat(result.get(0).getFacilities().size()).isEqualTo(1),
                () ->
                        assertThat(result.get(0).getFacilities().get(0).getFacilityId())
                                .isEqualTo(facility.getId()));
    }

    @Test
    @DisplayName("구와 동에 따라 카테고리를 만족하는 집의 위도와 경도 목록을 조회할 수 있다.")
    public void findHouseLatitudeAndLongitudeByGuAndDong() {
        Member member = save(MemberFixture.builder().build());
        House house = save(HouseFixture.builder().member(member).build());
        Facility facility = save(FacilityFixture.builder().build());
        save(HouseFacilityRelationFixture.builder().house(house).facility(facility).build());

        MapFilterRequest request =
                MapFilterRequest.of(
                        1,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE,
                        facility.getCategory().getName(),
                        10,
                        1,
                        house.getGu(),
                        house.getDong());
        List<Long> houseIdList = houseRepository.findHouseIdListByCategoryAndGuAndDong(request);
        List<HouseResult> result =
                houseRepository.findHouseLatitudeAndLongitude(request, houseIdList);

        assertAll(
                "result",
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).getLatitude()).isEqualTo(house.getLatitude()),
                () -> assertThat(result.get(0).getLongitude()).isEqualTo(house.getLongitude()),
                () -> assertThat(result.get(0).getFacilities().size()).isEqualTo(1),
                () ->
                        assertThat(result.get(0).getFacilities().get(0).getFacilityId())
                                .isEqualTo(facility.getId()));
    }

    @Test
    @DisplayName("지도 위치에 따라 집 목록을 최대 15개까지 조회할 수 있다.")
    public void findHouseContent() {
        Member member = save(MemberFixture.builder().build());
        for (int i = 0; i < 20; i++) save(HouseFixture.builder().member(member).build());

        MapFilterRequest request =
                MapFilterRequest.of(
                        5,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE);
        List<HouseContentResult> result = houseRepository.findHouseContent(request);

        assertAll("result", () -> assertThat(result.size()).isEqualTo(15));
    }

    @Test
    @DisplayName("회원은 집 목록 조회 시 자신이 북마크한 집인지 여부를 조회할 수 있다.")
    public void findHouseContentByMember() {
        Member agent = save(MemberFixture.builder().id(1L).build());
        Member member = save(MemberFixture.builder().id(2L).build());
        House isBookmark = save(HouseFixture.builder().member(agent).build());
        House isNotBookmark = save(HouseFixture.builder().member(agent).build());
        save(Bookmark.builder().member(member).house(isBookmark).build());

        MapFilterRequest request =
                MapFilterRequest.of(
                        9,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE);
        List<HouseContentResult> result = houseRepository.findHouseContent(request, member.getId());

        assertAll(
                "result",
                () -> assertThat(result.size()).isEqualTo(2),
                () -> assertThat(result.get(0).getHouseId()).isEqualTo(isBookmark.getId()),
                () -> assertThat(result.get(1).getHouseId()).isEqualTo(isNotBookmark.getId()),
                () -> assertThat(result.get(0).isBookmark()).isEqualTo(true),
                () -> assertThat(result.get(1).isBookmark()).isEqualTo(false));
    }

    @Test
    @DisplayName("지도 위치와 주소(구, 동)에 따라 집 목록을 최대 15개까지 조회할 수 있다.")
    public void findHouseContentByGuAndDong() {
        Member member = save(MemberFixture.builder().build());
        for (int i = 0; i < 20; i++) {
            House house = save(HouseFixture.builder().member(member).build());
            Facility facility = save(FacilityFixture.builder().build());
            save(HouseFacilityRelationFixture.builder().house(house).facility(facility).build());
        }

        MapFilterRequest request =
                MapFilterRequest.of(
                        5,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE,
                        "요식업",
                        10,
                        1,
                        "마포구",
                        "상암동");
        List<HouseContentResult> result =
                houseRepository.findHouseContent(
                        request, houseRepository.findHouseIdListByCategoryAndGuAndDong(request), member.getId());

        assertAll("result", () -> assertThat(result.size()).isEqualTo(15));
    }
}
