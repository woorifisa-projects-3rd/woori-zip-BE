package fisa.woorizip.backend.member.dto.request;

import fisa.woorizip.backend.member.domain.Gender;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
public class MemberProfileRequest {
    @Past LocalDate birthday;
    @NotNull private Gender gender;

    protected MemberProfileRequest() {}
}
