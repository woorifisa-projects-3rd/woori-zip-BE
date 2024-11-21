package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;

import java.time.LocalDate;

public class MemberFixture {

    private Long id;
    private String username = "rlfrkdms1";
    private String password = "password12!@";
    private String name = "길가은";
    private Role role = Role.MEMBER;
    private Long assets = 10000000L;
    private Long totalIncomeLastYear = 40000000L;
    private Integer yearsOfMarriage = 5;
    private Integer monthsOfEmployment = 60;
    private LocalDate birthday = LocalDate.parse("1990-01-01");
    private Integer creditScore = 800;

    public static MemberFixture builder() {
        return new MemberFixture();
    }

    public MemberFixture id(Long id) {
        this.id = id;
        return this;
    }

    public MemberFixture name(String name) {
        this.name = name;
        return this;
    }

    public MemberFixture username(String username) {
        this.username = username;
        return this;
    }

    public MemberFixture password(String password) {
        this.password = password;
        return this;
    }

    public MemberFixture role(Role role) {
        this.role = role;
        return this;
    }

    public MemberFixture birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public MemberFixture assets(Long assets) {
        this.assets = assets;
        return this;
    }

    public MemberFixture totalIncomeLastYear(Long totalIncomeLastYear) {
        this.totalIncomeLastYear = totalIncomeLastYear;
        return this;
    }

    public MemberFixture yearsOfMarriage(Integer yearsOfMarriage) {
        this.yearsOfMarriage = yearsOfMarriage;
        return this;
    }

    public MemberFixture monthsOfEmployment(Integer monthsOfEmployment) {
        this.monthsOfEmployment = monthsOfEmployment;
        return this;
    }

    public MemberFixture creditScore(Integer creditScore) {
        this.creditScore = creditScore;
        return this;
    }

    public Member build() {
        return Member.builder()
                .id(id)
                .username(username)
                .name(name)
                .password(password)
                .role(role)
                .assets(assets)
                .birthday(birthday)
                .creditScore(creditScore)
                .yearsOfMarriage(yearsOfMarriage)
                .monthsOfEmployment(monthsOfEmployment)
                .totalIncomeLastYear(totalIncomeLastYear)
                .build();
    }
}
