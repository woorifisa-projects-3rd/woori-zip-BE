package fisa.woorizip.backend.member.service.auth;

import fisa.woorizip.backend.member.domain.LifeStage;
import fisa.woorizip.backend.member.domain.Rank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ShowMemberDataResponse {

    private final String customerId;
    private final String name;
    private final LocalDate birthday;
    private final Integer creditScore;
    private final Rank rank;
    private final String address;
    private final LifeStage lifeStage;
    private final Long debt;
    private final SpendingHistoryResponse spendingHistory;

    @Builder
    private ShowMemberDataResponse(String customerId, String name, LocalDate birthday, Integer creditScore, Rank rank, String address, LifeStage lifeStage, Long debt, SpendingHistoryResponse spendingHistory) {
        this.customerId = customerId;
        this.name = name;
        this.birthday = birthday;
        this.creditScore = creditScore;
        this.rank = rank;
        this.address = address;
        this.lifeStage = lifeStage;
        this.debt = debt;
        this.spendingHistory = spendingHistory;
    }

//    public static ShowMemberDataResponse from(Member member) {
//        return ShowMemberDataResponse.builder()
//                .customerId(member.getCustomerId())
//                .name(member.getName())
//                .birthday(member.getBirthday())
//                .earningsType(member.getEarningsType())
//                .earningsFee(member.getEarningsFee())
//                .creditScore(member.getCreditScore())
//                .rank(member.getRank())
//                .address(member.getAddress())
//                .lifeStage(member.getLifeStage())
//                .debt(member.getDebt())
//                .spendingHistory(SpendingHistoryResponse.from(member.getSpendingHistory()))
//                .build();
//    }
}
