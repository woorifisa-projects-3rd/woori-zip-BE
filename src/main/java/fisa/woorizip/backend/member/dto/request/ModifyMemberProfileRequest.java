package fisa.woorizip.backend.member.dto.request;

import fisa.woorizip.backend.member.domain.Gender;
import fisa.woorizip.backend.member.domain.Member;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
public class ModifyMemberProfileRequest {
    @Past LocalDate birthday;
    @NotNull private Gender gender;

    protected ModifyMemberProfileRequest() {}

    public Member toMember() {
        return Member.builder().birthday(birthday).gender(gender).build();
    }
}
