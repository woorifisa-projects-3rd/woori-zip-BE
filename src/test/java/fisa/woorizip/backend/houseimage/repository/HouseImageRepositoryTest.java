package fisa.woorizip.backend.houseimage.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.houseimage.domain.HouseImage;
import fisa.woorizip.backend.support.RepositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

@RepositoryTest
class HouseImageRepositoryTest {

    @Autowired
    private HouseImageRepository houseImageRepository;

    @BeforeEach
    void setUp() {
        houseImageRepository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("provideOrderIndices")
    @DisplayName("매물 ID와 정렬 순서대로 이미지 URL을 조회한다")
    void findImageUrlsByHouseId(String testCase, List<Integer> orderIndices) {
        List<HouseImage> images = orderIndices.stream()
                .map(index -> HouseImage.builder()
                        .url("test" + (index + 1) + ".jpg")
                        .orderIndex(index)
                        .house(House.builder().id(1L).build())
                        .build())
                .toList();
        houseImageRepository.saveAll(images);

        List<String> imageUrls = houseImageRepository.findImageUrlsByHouseId(1L);

        List<String> expectedUrls = orderIndices.stream()
                .sorted()
                .map(index -> "test" + (index + 1) + ".jpg")
                .toList();

        assertThat(imageUrls)
                .hasSize(expectedUrls.size())
                .containsExactlyElementsOf(expectedUrls);
    }

    @ParameterizedTest
    @ValueSource(longs = {999L, -1L, 0L})
    @DisplayName("존재하지 않는 매물 ID 조회시 빈 리스트를 반환한다")
    void findImageUrlsByHouseId_NotFound(Long invalidHouseId) {
        List<String> imageUrls = houseImageRepository.findImageUrlsByHouseId(invalidHouseId);
        assertThat(imageUrls).isEmpty();
    }

    static Stream<Arguments> provideOrderIndices() {
        return Stream.of(
                arguments("정상적인 순서의 이미지", List.of(0, 1)),
                arguments("순서가 섞인 이미지", List.of(1, 0, 2)),
                arguments("불연속적인 순서의 이미지", List.of(0, 5, 10)),
                arguments("역순으로 정렬된 이미지", List.of(2, 1, 0)),
                arguments("동일한 orderIndex를 가진 이미지", List.of(1, 1, 1)),
                arguments("큰 간격의 orderIndex", List.of(0, 100, 200))
        );
    }
}