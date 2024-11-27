package fisa.woorizip.backend.member.service.auth;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.memberconsumption.domain.MemberConsumption;
import fisa.woorizip.backend.memberconsumption.domain.MemberConsumption.MemberConsumptionBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpendingHistoryResponse {

    private double culture;
    private double book;
    private double grocery;
    private double cloth;
    private double food;
    private double car;

    public MemberConsumption toMemberConsumption(Member member) {
        return createMemberConsumptionBuilder()
                .member(member)
                .build();
    }

    public MemberConsumption toMemberConsumption() {
        return createMemberConsumptionBuilder()
                .build();
    }

    private MemberConsumptionBuilder createMemberConsumptionBuilder() {
        return MemberConsumption.builder()
                .culture(culture)
                .book(book)
                .grocery(grocery)
                .cloth(cloth)
                .food(food)
                .car(car);
    }

}
