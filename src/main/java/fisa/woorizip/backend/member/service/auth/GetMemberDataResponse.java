package fisa.woorizip.backend.member.service.auth;

import fisa.woorizip.backend.member.domain.Gender;
import fisa.woorizip.backend.member.domain.LifeStage;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Membership;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.domain.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GetMemberDataResponse {

    private String customerId;
    private String name;
    private int gender;
    private LocalDate birthday;
    private Integer creditScore;
    private int membership;
    private LifeStage lifeStage;
    private Long assets;
    private int monthsOfEmployment;
    private int yearsOfMarriage;
    private long totalIncomeLastYear;
    private SpendingHistoryResponse spendingHistory;

    public Member toMember() {
        return Member.builder()
                .status(Status.NOT_ADMIN)
                .role(Role.MEMBER)
                .gender(Gender.from(gender))
                .membership(Membership.from(membership))
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
