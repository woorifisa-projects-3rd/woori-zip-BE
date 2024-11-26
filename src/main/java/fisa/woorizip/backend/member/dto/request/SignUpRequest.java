package fisa.woorizip.backend.member.dto.request;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Member.MemberBuilder;
import fisa.woorizip.backend.member.domain.Role;

import fisa.woorizip.backend.member.domain.Sex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@AllArgsConstructor
@Getter
public class SignUpRequest {

    @NotBlank private String name;
    @Email private String username;
    @NotBlank private String password;
    @Past private LocalDate birthday;
    @NotNull private Sex sex;
    private String licenseId;

    protected SignUpRequest() {}

    public MemberBuilder getMemberBuilder(String encodedPassword) {
        return Member.builder()
                .name(name)
                .username(username)
                .password(encodedPassword)
                .birthday(birthday);
    }

    public Member toAdminMember(String encodedPassword) {
        return getMemberBuilder(encodedPassword).role(Role.ADMIN).build();
    }

    public Member toAgentMember(String encodedPassword) {
        return getMemberBuilder(encodedPassword).licenseId(licenseId).role(Role.AGENT).build();
    }

}
