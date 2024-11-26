package fisa.woorizip.backend.member.service.auth;

import fisa.woorizip.backend.member.domain.LifeStage;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Rank;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.domain.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GetMemberDataResponse {

    private String customerId;
    private Sex sex;
    private String name;
    private LocalDate birthday;
    private Integer creditScore;
    private Rank rank;
    private String address;
    private LifeStage lifeStage;
    private Long assets;
    private int monthsOfEmployment;
    private int yearsOfMarriage;
    private long totalIncomeLastYear;
    private SpendingHistoryResponse spendingHistory;

    public Member toMember() {
        return Member.builder()
                .role(Role.MEMBER)
                .sex(sex)
                .rank(rank)
                .lifeStage(lifeStage)
                .creditScore(creditScore)
                .customerId(customerId)
                .name(name)
                .birthday(birthday)
                .assets(assets)
                .monthsOfEmployment(monthsOfEmployment)
                .totalIncomeLastYear(totalIncomeLastYear)
                .yearsOfMarriage(yearsOfMarriage)
                .build();
    }
}
