package fisa.woorizip.backend.member.dto.request;

import fisa.woorizip.backend.member.domain.EarningsType;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Past
    private LocalDate birthday;
    @NotBlank
    private String earningsType;
    @NotBlank
    private Long earningsFee;
    @NotBlank
    private Integer creditScore;

    private long debt;

    protected SignUpRequest() {
    }

    public SignUpRequest(String name, String username, String password, LocalDate birthday, String earningsType, Long earningsFee, Integer creditScore, long debt) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.earningsType = earningsType;
        this.earningsFee = earningsFee;
        this.creditScore = creditScore;
        this.debt = debt;
    }

    public Member toMember(String encodedPassword, Role role) {
        return Member.builder()
                .name(name)
                .username(username)
                .password(encodedPassword)
                .birthday(birthday)
                .earningsFee(earningsFee)
                .earningsType(EarningsType.from(earningsType))
                .creditScore(creditScore)
                .debt(debt)
                .role(role)
                .build();
    }
}
