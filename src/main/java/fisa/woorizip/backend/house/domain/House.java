package fisa.woorizip.backend.house.domain;

import fisa.woorizip.backend.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String comment; // 추가된 필드

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HousingExpenses housingExpenses;

    @Column(nullable = false, precision = 10, scale = 6)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 10, scale = 6)
    private BigDecimal longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HouseType houseType;

    @Column(nullable = false)
    private String gu;

    @Column(nullable = false)
    private String dong;

    @Column(nullable = false)
    private long deposit;

    @Column(nullable = false)
    private long monthlyRentFee;

    @Column(nullable = false)
    private long maintenanceFee;

    @Column(nullable = false)
    private int totalParkingSpaces;

    @Column(nullable = false)
    private int householdParkingSpaces;

    @Column(nullable = false)
    private String totalArea;

    @Column(nullable = false)
    private String exclusiveArea;

    @Column(nullable = false)
    private int rooms;

    @Column(nullable = false)
    private int bathrooms;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private int totalFloors;

    @Column(nullable = false)
    private LocalDate moveInDate;

    @Column(nullable = false)
    private LocalDate approvalDate;

    @Column(nullable = false)
    private String direction;

    // equals 및 hashCode 메서드 추가
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House house)) return false;
        return Objects.equals(id, house.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}