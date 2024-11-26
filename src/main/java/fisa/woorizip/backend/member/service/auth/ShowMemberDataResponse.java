package fisa.woorizip.backend.member.service.auth;

import fisa.woorizip.backend.member.domain.LifeStage;
import fisa.woorizip.backend.member.domain.Membership;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ShowMemberDataResponse {

    private final String customerId;
    private final String name;
    private final LocalDate birthday;
    private final Integer creditScore;
    private final Membership membership;
    private final String address;
    private final LifeStage lifeStage;
    private final Long debt;
    private final SpendingHistoryResponse spendingHistory;

    @Builder
    private ShowMemberDataResponse(String customerId, String name, LocalDate birthday, Integer creditScore, Membership membership, String address, LifeStage lifeStage, Long debt, SpendingHistoryResponse spendingHistory) {
        this.customerId = customerId;
        this.name = name;
        this.birthday = birthday;
        this.creditScore = creditScore;
        this.membership = membership;
        this.address = address;
        this.lifeStage = lifeStage;
        this.debt = debt;
        this.spendingHistory = spendingHistory;
    }
}
