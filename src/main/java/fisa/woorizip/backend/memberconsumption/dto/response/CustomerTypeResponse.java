package fisa.woorizip.backend.memberconsumption.dto.response;

import fisa.woorizip.backend.member.domain.Gender;
import fisa.woorizip.backend.member.domain.LifeStage;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Membership;
import fisa.woorizip.backend.memberconsumption.dto.Age;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

@Getter
public class CustomerTypeResponse {
    private Age age;
    private Gender gender;
    private Membership membership;
    private LifeStage lifeStage;

    private CustomerTypeResponse(
            Age age, Gender gender, Membership membership, LifeStage lifeStage) {
        this.age = age;
        this.gender = gender;
        this.membership = membership;
        this.lifeStage = lifeStage;
    }

    public static CustomerTypeResponse from(Member member) {
        return new CustomerTypeResponse(
                Age.from(Period.between(member.getBirthday(), LocalDate.now()).getYears()),
                member.getGender(),
                member.getMembership(),
                member.getLifeStage());
    }
}
