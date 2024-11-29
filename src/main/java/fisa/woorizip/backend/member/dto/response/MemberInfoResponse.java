package fisa.woorizip.backend.member.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import fisa.woorizip.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

import static fisa.woorizip.backend.member.domain.Role.AGENT;
import static fisa.woorizip.backend.member.domain.Role.MEMBER;

@Getter
@Builder
public class MemberInfoResponse {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String gender;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String membership;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String licenseId;

    public static MemberInfoResponse from(Member member) {
        return builder()
                .id(member.getId())
                .name(member.getName())
                .birthDate(member.getBirthday())
                .gender(member.getGender().getName())
                .membership(member.getRole() == MEMBER ? member.getMembership().getName() : null)
                .licenseId(member.getLicenseId())
                .build();
    }
}
