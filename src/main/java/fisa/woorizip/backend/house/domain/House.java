package fisa.woorizip.backend.house.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "housing_expenses", nullable = false)
    private HousingExpenses housingExpenses;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "house_type", nullable = false)
    private HouseType houseType;

    @Column(name = "gu", nullable = false)
    private String gu;

    @Column(name = "dong", nullable = false)
    private String dong;

    @Column(name = "deposit", nullable = false)
    private long deposit;

    @Column(name = "monthly_rent_fee", nullable = false)
    private long monthlyRentFee;

    @Column(name = "maintenance_fee", nullable = false)
    private long maintenanceFee;

    @Column(name = "total_parking_spaces", nullable = false)
    private int totalParkingSpaces;

    @Column(name = "household_parking_spaces", nullable = false)
    private int householdParkingSpaces;

    @Column(name = "total_area", nullable = false)
    private String totalArea;

    @Column(name = "exclusive_area", nullable = false)
    private String exclusiveArea;

    @Column(name = "rooms", nullable = false)
    private int rooms;

    @Column(name = "bathrooms", nullable = false)
    private int bathrooms;

    @Column(name = "floor", nullable = false)
    private int floor;

    @Column(name = "total_floors", nullable = false)
    private int totalFloors;

    @Column(name = "move_in_date", nullable = false)
    private LocalDate moveInDate;

    @Column(name = "approval_date", nullable = false)
    private LocalDate approvalDate;

    @Column(name = "direction", nullable = false)
    private String direction;
}
