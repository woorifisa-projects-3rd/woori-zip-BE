package fisa.woorizip.backend.member.domain;

import fisa.woorizip.backend.member.domain.bank.Gender;
import fisa.woorizip.backend.member.domain.bank.LifeStage;
import fisa.woorizip.backend.member.domain.bank.Membership;
import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership", nullable = false)
    private Membership membership;

    @Enumerated(EnumType.STRING)
    @Column(name = "life_stage", nullable = false)
    private LifeStage lifeStage;

    @Enumerated(EnumType.STRING)
    @Column(name = "earnings_type")
    private EarningsType earningsType;

    @Column(name = "earnings_fee")
    private Long earningsFee;

    @Column(name = "credit_score", nullable = false, columnDefinition = "MEDIUMINT DEFAULT 0")
    private int creditScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "assets", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private long assets;

    @Column(
            name = "total_income_last_year",
            nullable = false,
            columnDefinition = "BIGINT DEFAULT 0")
    private long totalIncomeLastYear;

    @Column(name = "years_of_marriage", nullable = false, columnDefinition = "MEDIUMINT DEFAULT 0")
    private int yearsOfMarriage;

    @Column(
            name = "months_of_employment",
            nullable = false,
            columnDefinition = "MEDIUMINT DEFAULT 0")
    private int monthsOfEmployment;
}
