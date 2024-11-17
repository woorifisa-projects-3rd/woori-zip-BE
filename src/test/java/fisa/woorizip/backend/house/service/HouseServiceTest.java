package fisa.woorizip.backend.house.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.dto.response.HouseDetailResponse;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.houseimage.repository.HouseImageRepository;
import fisa.woorizip.backend.support.fixture.HouseFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class HouseServiceTest {
    @Mock
    private HouseRepository houseRepository;

    @Mock
    private HouseImageRepository houseImageRepository;

    @InjectMocks
    private HouseServiceImpl houseService;

    @Test
    void 매물_상세정보와_이미지URL_조회_성공() {
        House testHouse = HouseFixture.builder().build();
        List<String> testImageUrls = List.of("test1.jpg", "test2.jpg");

        given(houseRepository.findById(1L)).willReturn(Optional.of(testHouse));
        given(houseImageRepository.findImageUrlsByHouseId(1L)).willReturn(testImageUrls);

        HouseDetailResponse houseDetailResponse = houseService.getHouseDetail(1L);

        assertThat(houseDetailResponse)
                .satisfies(response -> {
                    assertThat(response.getName()).isEqualTo(testHouse.getName());
                    assertThat(response.getImageUrls()).containsExactlyElementsOf(testImageUrls);
                });
    }

    @Test
    void 존재하지_않는_매물_조회시_예외발생() {
        given(houseRepository.findById(999L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> houseService.getHouseDetail(999L))
                .isInstanceOf(WooriZipException.class);
    }
}