package fisa.woorizip.backend.memberconsumption.domain;

import fisa.woorizip.backend.member.domain.Member;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "book", nullable = false)
    private double book;

    @Column(name = "car", nullable = false)
    private double car;

    @Column(name = "cloth", nullable = false)
    private double cloth;

    @Column(name = "culture", nullable = false)
    private double culture;

    @Column(name = "food", nullable = false)
    private double food;

    @Column(name = "grocery", nullable = false)
    private double grocery;

    private void updateBook(double book) {
        this.book = book;
    }

    private void updateCar(double car) {
        this.car = car;
    }

    private void updateCloth(double cloth) {
        this.cloth = cloth;
    }

    private void updateCulture(double culture) {
        this.culture = culture;
    }

    private void updateFood(double food) {
        this.food = food;
    }

    private void updateGrocery(double grocery) {
        this.grocery = grocery;
    }

    public void update(MemberConsumption memberConsumption) {
        updateBook(memberConsumption.getBook());
        updateCar(memberConsumption.getCar());
        updateCloth(memberConsumption.getCloth());
        updateCulture(memberConsumption.getCulture());
        updateFood(memberConsumption.getFood());
        updateGrocery(memberConsumption.getGrocery());
    }
}
