package fisa.woorizip.backend.house.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
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
        // given
        House house = HouseFixture.builder().build();
        List<String> imageUrls = List.of("test1.jpg", "test2.jpg");

        given(houseRepository.findById(anyLong())).willReturn(Optional.of(house));
        given(houseImageRepository.findImageUrlsByHouseId(anyLong())).willReturn(imageUrls);

        // when
        var response = houseService.getHouseDetail(1L);

        // then
        assertThat(response)
                .satisfies(r -> {
                    assertThat(r.getName()).isEqualTo(house.getName());
                    assertThat(r.getImageUrls()).containsExactlyElementsOf(imageUrls);
                });
    }

    @Test
    void 존재하지_않는_매물_조회시_예외발생() {
        // given
        given(houseRepository.findById(anyLong())).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> houseService.getHouseDetail(999L))
                .isInstanceOf(WooriZipException.class);
    }
}