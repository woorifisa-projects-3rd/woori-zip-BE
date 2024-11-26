package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.memberconsumption.domain.MemberConsumption;

public class MemberConsumptionFixture {
    private Long id;
    private Member member;
    private double book = 37.9228639;
    private double car = 0.8448971;
    private double cloth = 20.05107334;
    private double culture = 30.06974449;
    private double food = 6.1842673;
    private double grocery = 4.92715387;

    public static MemberConsumptionFixture builder() {
        return new MemberConsumptionFixture();
    }

    public MemberConsumptionFixture id(Long id) {
        this.id = id;
        return this;
    }

    public MemberConsumptionFixture member(Member member) {
        this.member = member;
        return this;
    }

    public MemberConsumptionFixture book(double book) {
        this.book = book;
        return this;
    }

    public MemberConsumptionFixture car(double car) {
        this.car = car;
        return this;
    }

    public MemberConsumptionFixture cloth(double cloth) {
        this.cloth = cloth;
        return this;
    }

    public MemberConsumptionFixture culture(double culture) {
        this.culture = culture;
        return this;
    }

    public MemberConsumptionFixture food(double food) {
        this.food = food;
        return this;
    }

    public MemberConsumptionFixture grocery(double grocery) {
        this.grocery = grocery;
        return this;
    }

    public MemberConsumption build() {
        return MemberConsumption.builder()
                .id(id)
                .member(member)
                .book(book)
                .car(car)
                .cloth(cloth)
                .culture(culture)
                .food(food)
                .grocery(grocery)
                .build();
    }
}
