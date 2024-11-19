package fisa.woorizip.backend.house.service;

import static fisa.woorizip.backend.house.dto.HouseAddressType.DONG;
import static fisa.woorizip.backend.house.dto.HouseAddressType.GU;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.facility.domain.Facility;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.HouseDetailResponse;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.house.dto.result.HouseContentResult;
import fisa.woorizip.backend.house.dto.result.HouseCountResult;
import fisa.woorizip.backend.house.dto.result.HouseResult;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.houseimage.repository.HouseImageRepository;
import fisa.woorizip.backend.support.fixture.FacilityFixture;
import fisa.woorizip.backend.support.fixture.HouseFixture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {
    @Mock private HouseRepository houseRepository;
    @Mock private HouseImageRepository houseImageRepository;
    @InjectMocks private HouseServiceImpl houseService;

    static final double SOUTH_WEST_LATITUDE = 37.452655162589174;
    static final double SOUTH_WEST_LONGITUDE = 126.79611509567208;
    static final double NORTH_EAST_LATITUDE = 37.654612635772615;
    static final double NORTH_EAST_LONGITUDE = 127.09945286237564;

    @Test
    void 매물_상세정보와_이미지URL_조회_성공() {
        House testHouse = HouseFixture.builder().build();
        List<String> testImageUrls = List.of("test1.jpg", "test2.jpg");

        given(houseRepository.findById(1L)).willReturn(Optional.of(testHouse));
        given(houseImageRepository.findImageUrlsByHouseId(1L)).willReturn(testImageUrls);

        HouseDetailResponse houseDetailResponse = houseService.getHouseDetail(1L);

        assertThat(houseDetailResponse)
                .satisfies(
                        response -> {
                            assertThat(response.getName()).isEqualTo(testHouse.getName());
                            assertThat(response.getImageUrls())
                                    .containsExactlyElementsOf(testImageUrls);
                        });
    }

    @Test
    void 존재하지_않는_매물_조회시_예외발생() {
        given(houseRepository.findById(999L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> houseService.getHouseDetail(999L))
                .isInstanceOf(WooriZipException.class);
    }

    @Test
    void 줌_레벨이_9_이상인_경우_구_별_집_개수를_조회할_수_있다() {
        House house = HouseFixture.builder().build();

        ShowMapResponse expected =
                ShowMapResponse.of(
                        GU,
                        List.of(HouseCountResult.init(house.getGu(), 1)),
                        List.of(HouseContentResult.init(house)));
        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        9,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE);

        given(houseRepository.findHouseHighLevel(any(MapFilterRequest.class))).willReturn(expected);
        ShowMapResponse response = houseService.showMap(mapFilterRequest);

        assertAll(
                () -> verify(houseRepository, times(1)).findHouseHighLevel(mapFilterRequest),
                () ->
                        assertThat(expected.getHouseAddressType())
                                .isEqualTo(response.getHouseAddressType()),
                () ->
                        assertThat(expected.getCounts().get(0).getAddressName())
                                .isEqualTo(response.getCounts().get(0).getAddressName()),
                () ->
                        assertThat(expected.getCounts().get(0).getCount())
                                .isEqualTo(response.getCounts().get(0).getCount()));
    }

    @Test
    void 줌_레벨이_6_이상이고_8_이하인_경우_동_별_집_개수를_조회할_수_있다() {
        House house = HouseFixture.builder().build();

        ShowMapResponse expected =
                ShowMapResponse.of(
                        DONG,
                        List.of(HouseCountResult.init(house.getDong(), 1)),
                        List.of(HouseContentResult.init(house)));
        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        8,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE);

        given(houseRepository.findHouseMidLevel(any(MapFilterRequest.class))).willReturn(expected);
        ShowMapResponse response = houseService.showMap(mapFilterRequest);

        assertAll(
                () -> verify(houseRepository, times(1)).findHouseMidLevel(mapFilterRequest),
                () ->
                        assertThat(expected.getHouseAddressType())
                                .isEqualTo(response.getHouseAddressType()),
                () ->
                        assertThat(expected.getCounts().get(0).getAddressName())
                                .isEqualTo(response.getCounts().get(0).getAddressName()),
                () ->
                        assertThat(expected.getCounts().get(0).getCount())
                                .isEqualTo(response.getCounts().get(0).getCount()));
    }

    @Test
    void 줌_레벨이_5_이하인_경우_해당_범위_내_모든_집의_위도와_경도를_조회할_수_있다() {
        House house = HouseFixture.builder().build();

        ShowMapResponse expected =
                ShowMapResponse.of(
                        List.of(HouseResult.init(house)), List.of(HouseContentResult.init(house)));
        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        5,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE);

        given(houseRepository.findHouseLowLevel(any(MapFilterRequest.class))).willReturn(expected);
        ShowMapResponse response = houseService.showMap(mapFilterRequest);

        assertAll(
                () -> verify(houseRepository, times(1)).findHouseLowLevel(mapFilterRequest),
                () ->
                        assertThat(expected.getHouses().get(0).getLatitude())
                                .isEqualTo(response.getHouses().get(0).getLatitude()),
                () ->
                        assertThat(expected.getHouses().get(0).getLongitude())
                                .isEqualTo(response.getHouses().get(0).getLongitude()));
    }

    @Test
    void 줌_레벨이_9_이상이고_카테고리가_있는_경우_구_별_집_개수를_조회할_수_있다() {
        House house = HouseFixture.builder().build();

        ShowMapResponse expected =
                ShowMapResponse.of(
                        GU,
                        List.of(HouseCountResult.init(house.getGu(), 1)),
                        List.of(HouseContentResult.init(house)));
        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        9,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE,
                        "요식업",
                        10,
                        2);

        given(houseRepository.findHouseHighLevelInCategory(any(MapFilterRequest.class)))
                .willReturn(expected);
        ShowMapResponse response = houseService.showMap(mapFilterRequest);

        assertAll(
                () ->
                        verify(houseRepository, times(1))
                                .findHouseHighLevelInCategory(mapFilterRequest),
                () ->
                        assertThat(expected.getHouseAddressType())
                                .isEqualTo(response.getHouseAddressType()),
                () ->
                        assertThat(expected.getCounts().get(0).getAddressName())
                                .isEqualTo(response.getCounts().get(0).getAddressName()),
                () ->
                        assertThat(expected.getCounts().get(0).getCount())
                                .isEqualTo(response.getCounts().get(0).getCount()));
    }

    @Test
    void 줌_레벨이_6_이상이고_8_이하이며_카테고리가_있는_경우_동_별_집_개수를_조회할_수_있다() {
        House house = HouseFixture.builder().build();

        ShowMapResponse expected =
                ShowMapResponse.of(
                        DONG,
                        List.of(HouseCountResult.init(house.getDong(), 1)),
                        List.of(HouseContentResult.init(house)));
        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        6,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE,
                        "요식업",
                        10,
                        2);

        given(houseRepository.findHouseMidLevelInCategory(any(MapFilterRequest.class)))
                .willReturn(expected);
        ShowMapResponse response = houseService.showMap(mapFilterRequest);

        assertAll(
                () ->
                        verify(houseRepository, times(1))
                                .findHouseMidLevelInCategory(mapFilterRequest),
                () ->
                        assertThat(expected.getHouseAddressType())
                                .isEqualTo(response.getHouseAddressType()),
                () ->
                        assertThat(expected.getCounts().get(0).getAddressName())
                                .isEqualTo(response.getCounts().get(0).getAddressName()),
                () ->
                        assertThat(expected.getCounts().get(0).getCount())
                                .isEqualTo(response.getCounts().get(0).getCount()));
    }

    @Test
    void 줌_레벨이_5_이하이고_카테고리가_있는_경우_해당_범위_내_모든_집의_위도와_경도_및_집에_연결된_시설을_조회할_수_있다() {
        House house = HouseFixture.builder().build();
        Facility facility = FacilityFixture.builder().build();

        ShowMapResponse expected =
                ShowMapResponse.of(
                        List.of(HouseResult.init(house, List.of(facility))),
                        List.of(HouseContentResult.init(house)));
        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        1,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE,
                        "요식업",
                        10,
                        1);

        given(houseRepository.findHouseLowLevelInCategory(any(MapFilterRequest.class)))
                .willReturn(expected);
        ShowMapResponse response = houseService.showMap(mapFilterRequest);

        assertAll(
                () ->
                        verify(houseRepository, times(1))
                                .findHouseLowLevelInCategory(mapFilterRequest),
                () ->
                        assertThat(expected.getHouses().get(0).getLatitude())
                                .isEqualTo(response.getHouses().get(0).getLatitude()),
                () ->
                        assertThat(expected.getHouses().get(0).getLongitude())
                                .isEqualTo(response.getHouses().get(0).getLongitude()),
                () ->
                        assertThat(expected.getHouses().get(0).getFacilities().get(0).getLatitude())
                                .isEqualTo(
                                        response.getHouses()
                                                .get(0)
                                                .getFacilities()
                                                .get(0)
                                                .getLatitude()),
                () ->
                        assertThat(
                                        expected.getHouses()
                                                .get(0)
                                                .getFacilities()
                                                .get(0)
                                                .getLongitude())
                                .isEqualTo(
                                        response.getHouses()
                                                .get(0)
                                                .getFacilities()
                                                .get(0)
                                                .getLongitude()));
    }

    @Test
    void 카테고리와_구_및_동으로_지도를_조회하는_경우_집을_조회할_수_있다() {
        House house = HouseFixture.builder().build();
        Facility facility = FacilityFixture.builder().build();

        ShowMapResponse expected =
                ShowMapResponse.of(
                        List.of(HouseResult.init(house, List.of(facility))),
                        List.of(HouseContentResult.init(house)));
        MapFilterRequest mapFilterRequest =
                MapFilterRequest.of(
                        5,
                        SOUTH_WEST_LATITUDE,
                        SOUTH_WEST_LONGITUDE,
                        NORTH_EAST_LATITUDE,
                        NORTH_EAST_LONGITUDE,
                        "요식업",
                        10,
                        1,
                        house.getGu(),
                        house.getDong());

        given(houseRepository.findHouseByGuAndDongInCategory(any(MapFilterRequest.class)))
                .willReturn(expected);
        ShowMapResponse response = houseService.showMap(mapFilterRequest);

        assertAll(
                () ->
                        verify(houseRepository, times(1))
                                .findHouseByGuAndDongInCategory(mapFilterRequest),
                () ->
                        assertThat(expected.getHouseContents().get(0).getGu())
                                .isEqualTo(response.getHouseContents().get(0).getGu()),
                () ->
                        assertThat(expected.getHouseContents().get(0).getDong())
                                .isEqualTo(response.getHouseContents().get(0).getDong()));
    }
}
