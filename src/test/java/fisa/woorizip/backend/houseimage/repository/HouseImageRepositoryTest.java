package fisa.woorizip.backend.houseimage.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.houseimage.domain.HouseImage;
import fisa.woorizip.backend.support.RepositoryTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@RepositoryTest
@Transactional
class HouseImageRepositoryTest {

    @Autowired private HouseImageRepository houseImageRepository;

    @ParameterizedTest
    @MethodSource("provideImagesTestCases")
    @DisplayName("매물 ID와 정렬 순서대로 이미지 URL을 조회한다")
    void findImageUrlsByHouseId(
            String testCase, List<HouseImage> images, List<String> expectedUrls) {
        List<String> imageUrls =
                houseImageRepository.findImageUrlsByHouseId(1L); // DB에 있는 실제 house_id

        assertThat(imageUrls).hasSize(expectedUrls.size()).containsExactlyElementsOf(expectedUrls);
    }

    @ParameterizedTest
    @ValueSource(longs = {999L, -1L, 0L})
    @DisplayName("존재하지 않는 매물 ID 조회시 빈 리스트를 반환한다")
    void findImageUrlsByHouseId_NotFound(Long invalidHouseId) {
        List<String> imageUrls = houseImageRepository.findImageUrlsByHouseId(invalidHouseId);

        assertThat(imageUrls).isEmpty();
    }

    static Stream<Arguments> provideImagesTestCases() {
        return Stream.of(
                arguments(
                        "정상적인 순서의 이미지",
                        List.of(
                                HouseImage.builder()
                                        .url("test1.jpg")
                                        .orderIndex(0)
                                        .house(House.builder().id(1L).build())
                                        .build(),
                                HouseImage.builder()
                                        .url("test2.jpg")
                                        .orderIndex(1)
                                        .house(House.builder().id(1L).build())
                                        .build()),
                        List.of("test1.jpg", "test2.jpg")),
                arguments(
                        "순서가 섞인 이미지",
                        List.of(
                                HouseImage.builder()
                                        .url("test2.jpg")
                                        .orderIndex(1)
                                        .house(House.builder().id(1L).build())
                                        .build(),
                                HouseImage.builder()
                                        .url("test1.jpg")
                                        .orderIndex(0)
                                        .house(House.builder().id(1L).build())
                                        .build(),
                                HouseImage.builder()
                                        .url("test3.jpg")
                                        .orderIndex(2)
                                        .house(House.builder().id(1L).build())
                                        .build()),
                        List.of("test1.jpg", "test2.jpg", "test3.jpg")),
                arguments(
                        "orderIndex가 불연속적인 경우",
                        List.of(
                                HouseImage.builder()
                                        .url("test1.jpg")
                                        .orderIndex(0)
                                        .house(House.builder().id(1L).build())
                                        .build(),
                                HouseImage.builder()
                                        .url("test2.jpg")
                                        .orderIndex(5)
                                        .house(House.builder().id(1L).build())
                                        .build(),
                                HouseImage.builder()
                                        .url("test3.jpg")
                                        .orderIndex(10)
                                        .house(House.builder().id(1L).build())
                                        .build()),
                        List.of("test1.jpg", "test2.jpg", "test3.jpg")));
    }
}
