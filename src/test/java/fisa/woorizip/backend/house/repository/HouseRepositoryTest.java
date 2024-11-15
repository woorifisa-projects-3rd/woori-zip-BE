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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

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
        // given
        Member member = memberRepository.save(MemberFixture.builder().build());
        House house = HouseFixture.builder()
                .member(member)
                .build();
        House savedHouse = houseRepository.save(house);

        // when
        var foundHouse = houseRepository.findById(savedHouse.getId());

        // then
        assertThat(foundHouse)
                .isPresent()
                .hasValueSatisfying(h -> {
                    assertThat(h.getName()).isEqualTo(house.getName());
                    assertThat(h.getAddress()).isEqualTo(house.getAddress());
                    assertThat(h.getHouseType()).isEqualTo(house.getHouseType());
                    assertThat(h.getHousingExpenses()).isEqualTo(house.getHousingExpenses());
                });
    }

    @Test
    @DisplayName("존재하지 않는 매물 ID로 조회하면 빈 Optional을 반환한다")
    void findById_NotFound() {
        // when
        var result = houseRepository.findById(999L);

        // then
        assertThat(result).isEmpty();
    }
}
