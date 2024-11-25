package fisa.woorizip.backend.member.dto.request;

import fisa.woorizip.backend.member.domain.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpRequest {

    @NotBlank private String name;
    @NotBlank private String username;
    @NotBlank private String password;
    @Past private LocalDate birthday;
    @NotNull private Gender gender;
    private Membership membership;
    private LifeStage lifeStage;
    @NotNull private Integer creditScore;
    private long assets;
    private long totalIncomeLastYear;
    private Integer yearsOfMarriage;
    private Integer monthsOfEmployment;

    protected SignUpRequest() {}

    public SignUpRequest(
            String name,
            String username,
            String password,
            LocalDate birthday,
            Integer creditScore,
            long assets,
            long totalIncomeLastYear,
            Integer yearsOfMarriage,
            Integer monthsOfEmployment,
            Gender gender,
            Membership membership,
            LifeStage lifeStage) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.creditScore = creditScore;
        this.assets = assets;
        this.totalIncomeLastYear = totalIncomeLastYear;
        this.yearsOfMarriage = yearsOfMarriage;
        this.monthsOfEmployment = monthsOfEmployment;
        this.gender = gender;
        this.membership = membership;
        this.lifeStage = lifeStage;
    }

    public Member toMember(String encodedPassword, Role role) {
        return Member.builder()
                .name(name)
                .username(username)
                .password(encodedPassword)
                .birthday(birthday)
                .creditScore(creditScore)
                .role(role)
                .assets(assets)
                .totalIncomeLastYear(totalIncomeLastYear)
                .yearsOfMarriage(yearsOfMarriage)
                .monthsOfEmployment(monthsOfEmployment)
                .gender(gender)
                .membership(membership)
                .lifeStage(lifeStage)
                .build();
    }
}
