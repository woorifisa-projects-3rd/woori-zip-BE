package fisa.woorizip.backend.member.dto.request;

import fisa.woorizip.backend.member.domain.Gender;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Member.MemberBuilder;
import fisa.woorizip.backend.member.domain.Role;

import fisa.woorizip.backend.member.domain.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank private String name;
    @Email private String username;
    @NotBlank private String password;
    @Past private LocalDate birthday;
    @NotNull private Gender gender;
    private String licenseId;

    protected SignUpRequest() {}

    public MemberBuilder getMemberBuilder(String encodedPassword) {
        return Member.builder()
                .name(name)
                .username(username)
                .password(encodedPassword)
                .birthday(birthday)
                .gender(gender);
    }

    public Member toAdminMember(String encodedPassword) {
        return getMemberBuilder(encodedPassword).role(Role.ADMIN).status(Status.PENDING_APPROVAL).build();
    }

    public Member toAgentMember(String encodedPassword) {
        return getMemberBuilder(encodedPassword).licenseId(licenseId).role(Role.AGENT).status(Status.NOT_ADMIN).build();
    }

}
