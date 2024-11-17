package fisa.woorizip.backend.house.repository;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.HouseFixture;
import fisa.woorizip.backend.support.fixture.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RepositoryTest
@Transactional
class HouseRepositoryTest {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("매물 ID로 매물을 조회할 수 있다")
    void findById() {
        Member savedMember = memberRepository.save(MemberFixture.builder().build());
        House newHouse = HouseFixture.builder()
                .member(savedMember)
                .build();
        House savedHouse = houseRepository.save(newHouse);

        Optional<House> actualHouse = houseRepository.findById(savedHouse.getId());

        assertThat(actualHouse)
                .isPresent()
                .hasValueSatisfying(foundHouse -> {
                    assertThat(foundHouse.getName()).isEqualTo(newHouse.getName());
                    assertThat(foundHouse.getAddress()).isEqualTo(newHouse.getAddress());
                    assertThat(foundHouse.getHouseType()).isEqualTo(newHouse.getHouseType());
                    assertThat(foundHouse.getHousingExpenses()).isEqualTo(newHouse.getHousingExpenses());
                });
    }

    @Test
    @DisplayName("존재하지 않는 매물 ID로 조회하면 빈 Optional을 반환한다")
    void findById_NotFound() {
        Optional<House> houseLookupResult = houseRepository.findById(999L);

        assertThat(houseLookupResult).isEmpty();
    }
}