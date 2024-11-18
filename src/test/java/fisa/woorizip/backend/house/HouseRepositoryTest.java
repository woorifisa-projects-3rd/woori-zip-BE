package fisa.woorizip.backend.house;

import fisa.woorizip.backend.facility.domain.Facility;
import fisa.woorizip.backend.facility.repository.FacilityRepository;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.house.repository.HouseRepository;
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

import static fisa.woorizip.backend.house.dto.HouseAddressType.DONG;
import static fisa.woorizip.backend.house.dto.HouseAddressType.GU;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

@RepositoryTest
public class HouseRepositoryTest {
    @Autowired private HouseRepository houseRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private FacilityRepository facilityRepository;
    @Autowired private HouseFacilityRelationRepository houseFacilityRelationRepository;

    private House save(House house) {
        return houseRepository.save(house);
    }

    private Member save(Member member) {
        return memberRepository.save(member);
    }

    private Facility save(Facility facility) { return facilityRepository.save(facility); }

    private HouseFacilityRelation save(HouseFacilityRelation houseFacilityRelation) { return houseFacilityRelationRepository.save(houseFacilityRelation); }

    @Test
    @DisplayName("지도 줌이 9 레벨 이상일 때, 구 별 집 개수와 15개의 집 목록을 조회할 수 있다.")
    public void findHouseHighLevel() {
        Member member = save(MemberFixture.builder().build());

        for (long i = 1; i <= 10; i++) save(HouseFixture.builder().member(member).build());
        for (long i = 11; i <= 20; i++)
            save(HouseFixture.builder().member(member).gu("강동구").dong("성내동").build());
        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        9,
                        37.452655162589174,
                        126.79611509567208,
                        37.654612635772615,
                        127.09945286237564);
        ShowMapResponse result = houseRepository.findHouseHighLevel(mapFilterRequest);

        assertAll(
                "response",
                () -> assertThat(result.getHouseAddressType().equals(GU)),
                () -> assertThat(result.getCounts().size() == 2),
                () -> assertThat(result.getCounts().get(0).getCount() == 10),
                () -> assertThat(result.getCounts().get(0).getAddressName().equals("강동구")),
                () -> assertThat(result.getCounts().get(1).getCount() == 10),
                () -> assertThat(result.getCounts().get(0).getAddressName().equals("마포구")),
                () -> assertThat(result.getHouseContents().size() == 15));
    }

    @Test
    @DisplayName("지도 줌이 6 레벨부터 8 레벨 사이일 때, 동 별 집 개수와 15개의 집 목록을 조회할 수 있다.")
    public void findHouseMidLevel() {
        Member member = save(MemberFixture.builder().build());

        for (long i = 1; i <= 10; i++) save(HouseFixture.builder().member(member).build());
        for (long i = 11; i <= 20; i++)
            save(HouseFixture.builder().member(member).dong("아현동").build());
        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        6,
                        37.452655162589174,
                        126.79611509567208,
                        37.654612635772615,
                        127.09945286237564);
        ShowMapResponse result = houseRepository.findHouseMidLevel(mapFilterRequest);

        assertAll(
                "response",
                () -> assertThat(result.getHouseAddressType().equals(DONG)),
                () -> assertThat(result.getCounts().size() == 2),
                () -> assertThat(result.getCounts().get(0).getCount() == 10),
                () -> assertThat(result.getCounts().get(0).getAddressName().equals("상암동")),
                () -> assertThat(result.getCounts().get(1).getCount() == 10),
                () -> assertThat(result.getCounts().get(0).getAddressName().equals("아현동")),
                () -> assertThat(result.getHouseContents().size() == 15));
    }

    @Test
    @DisplayName("지도 줌이 5 레벨 이하일 때, 지도 범위 내에 있는 모든 집 위도 경도 목록과 15개의 집 목록을 조회할 수 있다.")
    public void findHouseLowLevel() {
        Member member = save(MemberFixture.builder().build());

        House house1 =
                save(
                        HouseFixture.builder()
                                .latitude(37.452655162589175)
                                .longitude(126.79611509567209)
                                .member(member)
                                .build());
        House house2 =
                save(
                        HouseFixture.builder()
                                .latitude(37.654612635772614)
                                .longitude(127.09945286237563)
                                .member(member)
                                .build());

        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        5,
                        37.452655162589174,
                        126.79611509567208,
                        37.654612635772615,
                        127.09945286237564);
        ShowMapResponse result = houseRepository.findHouseLowLevel(mapFilterRequest);

        assertAll(
                "response",
                () -> assertThat(result.getHouses().get(0).getHouseId().equals(house1.getId())),
                () ->
                        assertThat(result.getHouses().get(0).getLatitude())
                                .isEqualTo(house1.getLatitude()),
                () ->
                        assertThat(result.getHouses().get(0).getLongitude())
                                .isEqualTo(house1.getLongitude()),
                () -> assertThat(result.getHouses().get(1).getHouseId().equals(house2.getId())),
                () ->
                        assertThat(result.getHouses().get(1).getLatitude())
                                .isEqualTo(house2.getLatitude()),
                () ->
                        assertThat(result.getHouses().get(1).getLongitude())
                                .isEqualTo(house2.getLongitude()),
                () -> assertThat(result.getHouseContents().size() == 15));
    }

    @Test
    @DisplayName("지도 줌이 9 레벨 이상이고 카테고리가 존재할 때, 구 별 집 개수와 15개의 집 목록을 조회할 수 있다.")
    public void findHouseHighLevelInCategory() {
        Member member = save(MemberFixture.builder().build());

        House house1 =
                save(
                        HouseFixture.builder()
                                .latitude(37.452655162589175)
                                .longitude(126.79611509567209)
                                .member(member)
                                .build());
        House house2 =
                save(
                        HouseFixture.builder()
                                .latitude(37.654612635772614)
                                .longitude(127.09945286237563)
                                .member(member)
                                .build());

        Facility facility1 = save(FacilityFixture.builder().build());
        Facility facility2 = save(FacilityFixture.builder().build());

        HouseFacilityRelation relation1 = save(HouseFacilityRelationFixture.builder().house(house1).facility(facility1).build());
        HouseFacilityRelation relation2 = save(HouseFacilityRelationFixture.builder().house(house1).facility(facility2).build());
        HouseFacilityRelation relation3 = save(HouseFacilityRelationFixture.builder().house(house2).facility(facility1).build());

        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        9,
                        37.452655162589174,
                        126.79611509567208,
                        37.654612635772615,
                        127.09945286237564,
                        "요식업",
                        10,
                        2
                );
        ShowMapResponse result = houseRepository.findHouseHighLevelInCategory(mapFilterRequest);

        assertAll(
                "response",
                () -> assertThat(result.getHouseAddressType().equals(GU)),
                () -> assertThat(result.getCounts().size() == 1),
                () -> assertThat(result.getCounts().get(0).getAddressName().equals(house1.getGu())),
                () -> assertThat(result.getCounts().get(0).getCount() == 1));
    }

    @Test
    @DisplayName("지도 줌이 6 레벨부터 8 레벨 사이이고 카테고리가 존재할 때, 동 별 집 개수와 15개의 집 목록을 조회할 수 있다.")
    public void findHouseMidLevelInCategory() {
        Member member = save(MemberFixture.builder().build());

        House house1 =
                save(
                        HouseFixture.builder()
                                .latitude(37.452655162589175)
                                .longitude(126.79611509567209)
                                .member(member)
                                .build());
        House house2 =
                save(
                        HouseFixture.builder()
                                .latitude(37.654612635772614)
                                .longitude(127.09945286237563)
                                .member(member)
                                .build());

        Facility facility1 = save(FacilityFixture.builder().build());
        Facility facility2 = save(FacilityFixture.builder().build());

        HouseFacilityRelation relation1 = save(HouseFacilityRelationFixture.builder().house(house1).facility(facility1).build());
        HouseFacilityRelation relation2 = save(HouseFacilityRelationFixture.builder().house(house1).facility(facility2).build());
        HouseFacilityRelation relation3 = save(HouseFacilityRelationFixture.builder().house(house2).facility(facility1).build());

        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        7,
                        37.452655162589174,
                        126.79611509567208,
                        37.654612635772615,
                        127.09945286237564,
                        "요식업",
                        10,
                        2
                );
        ShowMapResponse result = houseRepository.findHouseHighLevelInCategory(mapFilterRequest);

        assertAll(
                "response",
                () -> assertThat(result.getHouseAddressType().equals(DONG)),
                () -> assertThat(result.getCounts().size() == 1),
                () -> assertThat(result.getCounts().get(0).getAddressName().equals(house1.getDong())),
                () -> assertThat(result.getCounts().get(0).getCount() == 1));
    }

    @Test
    @DisplayName("지도 줌이 5 레벨 이하이고 카테고리가 존재할 때, 지도 범위 내에 있는 모든 집 위도 경도 목록과 15개의 집 목록을 조회할 수 있다.")
    public void findHouseLowLevelInCategory() {
        Member member = save(MemberFixture.builder().build());

        House house1 =
                save(
                        HouseFixture.builder()
                                .latitude(37.452655162589175)
                                .longitude(126.79611509567209)
                                .member(member)
                                .build());
        House house2 =
                save(
                        HouseFixture.builder()
                                .latitude(37.654612635772614)
                                .longitude(127.09945286237563)
                                .member(member)
                                .build());

        Facility facility1 = save(FacilityFixture.builder().build());
        Facility facility2 = save(FacilityFixture.builder().build());

        HouseFacilityRelation relation1 = save(HouseFacilityRelationFixture.builder().house(house1).facility(facility1).build());
        HouseFacilityRelation relation2 = save(HouseFacilityRelationFixture.builder().house(house1).facility(facility2).build());
        HouseFacilityRelation relation3 = save(HouseFacilityRelationFixture.builder().house(house2).facility(facility1).build());

        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        5,
                        37.452655162589174,
                        126.79611509567208,
                        37.654612635772615,
                        127.09945286237564,
                        "요식업",
                        10,
                        2
                        );
        ShowMapResponse result = houseRepository.findHouseLowLevelInCategory(mapFilterRequest);

        assertAll(
                "response",
                () -> assertThat(result.getHouses().size() == 1),
                () -> assertThat(result.getHouses().get(0).getHouseId().equals(house1.getId())),
                () ->
                        assertThat(result.getHouses().get(0).getLatitude())
                                .isEqualTo(house1.getLatitude()),
                () ->
                        assertThat(result.getHouses().get(0).getLongitude())
                                .isEqualTo(house1.getLongitude()),
                () -> assertThat(result.getHouses().get(0).getFacilities().size() == 2),
                () -> assertThat(result.getHouses().get(0).getFacilities().get(0).getLatitude()).isEqualTo(facility1.getLatitude()),
                () -> assertThat(result.getHouses().get(0).getFacilities().get(1).getLongitude()).isEqualTo(facility1.getLongitude()),
                () -> assertThat(result.getHouseContents().size() == 15));
    }
}
