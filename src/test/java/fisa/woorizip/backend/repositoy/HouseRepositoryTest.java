package fisa.woorizip.backend.repositoy;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.support.RepositoryTest;
import fisa.woorizip.backend.support.fixture.HouseFixture;
import fisa.woorizip.backend.support.fixture.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static fisa.woorizip.backend.house.dto.HouseAddressType.GU;
import static fisa.woorizip.backend.member.domain.Role.MEMBER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

@RepositoryTest
public class HouseRepositoryTest {
    @Autowired private HouseRepository houseRepository;
    @Autowired private MemberRepository memberRepository;

    private House save(House house) {
        return houseRepository.save(house);
    }

    private Member save(Member member) {
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("지도 줌이 9 레벨 이상일 때, 구 별 집 개수와 15개의 집 목록을 조회할 수 있다.")
    public void findHouseHighLevel() {
        Member member =
                save(
                        Member.builder()
                                .id(1L)
                                .name("길가은")
                                .password("123")
                                .username("1234")
                                .debt(1L)
                                .role(MEMBER)
                                .build());

        for (long i = 1; i <= 10; i++) save(HouseFixture.builder().id(i).member(member).build());
        for (long i = 11; i <= 20; i++)
            save(HouseFixture.builder().id(i).member(member).gu("강동구").dong("성내동").build());
        MapFilterRequest mapFilterRequest = MapFilterRequest.of(9, 37.452655162589174,
                126.79611509567208, 37.654612635772615,
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
}
