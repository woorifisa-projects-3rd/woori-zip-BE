package fisa.woorizip.backend.houseimage.repository;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.houseimage.domain.HouseImage;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.HouseFixture;
import fisa.woorizip.backend.support.fixture.HouseImageFixture;
import fisa.woorizip.backend.support.fixture.MemberFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RepositoryTest
@Transactional
class HouseImageRepositoryTest {

    @Autowired
    private HouseImageRepository houseImageRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private MemberRepository memberRepository;

    private House savedHouse;

    @BeforeEach
    void setUp() {
        Member member = memberRepository.save(MemberFixture.builder().build());
        savedHouse = houseRepository.save(HouseFixture.builder()
                .member(member)
                .build());
    }

    @Test
    @DisplayName("매물 ID로 이미지 URL들을 순서대로 조회할 수 있다")
    void findImageUrlsByHouseId() {
        // given
        List<HouseImage> images = List.of(
                HouseImageFixture.builder()
                        .url("test1.jpg")
                        .orderIndex(0)
                        .build(savedHouse),
                HouseImageFixture.builder()
                        .url("test2.jpg")
                        .orderIndex(1)
                        .build(savedHouse)
        );
        houseImageRepository.saveAll(images);

        // when
        List<String> imageUrls = houseImageRepository.findImageUrlsByHouseId(savedHouse.getId());

        // then
        assertThat(imageUrls)
                .hasSize(2)
                .containsExactly("test1.jpg", "test2.jpg");
    }

    @Test
    @DisplayName("매물에 이미지가 없으면 빈 리스트를 반환한다")
    void findImageUrlsByHouseId_Empty() {
        // when
        List<String> imageUrls = houseImageRepository.findImageUrlsByHouseId(savedHouse.getId());

        // then
        assertThat(imageUrls).isEmpty();
    }

    @Test
    @DisplayName("이미지 URL들이 orderIndex 순서대로 정렬되어 조회된다")
    void findImageUrlsByHouseId_OrderedByIndex() {
        // given
        List<HouseImage> images = List.of(
                HouseImageFixture.builder()
                        .url("test2.jpg")
                        .orderIndex(1)
                        .build(savedHouse),
                HouseImageFixture.builder()
                        .url("test1.jpg")
                        .orderIndex(0)
                        .build(savedHouse),
                HouseImageFixture.builder()
                        .url("test3.jpg")
                        .orderIndex(2)
                        .build(savedHouse)
        );
        houseImageRepository.saveAll(images);

        // when
        List<String> imageUrls = houseImageRepository.findImageUrlsByHouseId(savedHouse.getId());

        // then
        assertThat(imageUrls)
                .hasSize(3)
                .containsExactly("test1.jpg", "test2.jpg", "test3.jpg");
    }
}