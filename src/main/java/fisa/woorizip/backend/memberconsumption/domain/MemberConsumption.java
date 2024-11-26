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
}
