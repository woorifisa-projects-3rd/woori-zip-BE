package fisa.woorizip.backend.memberconsumption;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.member.service.MemberService;
import fisa.woorizip.backend.support.ControllerTest;
import fisa.woorizip.backend.support.fixture.MemberFixture;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.springframework.http.HttpStatus.OK;

public class MemberConsumptionControllerTest extends ControllerTest {

    @Test
    void 회원의_소비_패턴_분석_결과를_조회할_수_있다() {
        Member member = MemberFixture.builder().id(1L).build();

        baseRestAssuredWithAuth(member)
                .when()
                .get("api/v1/consumption")
                .then()
                .statusCode(OK.value());
    }
}
