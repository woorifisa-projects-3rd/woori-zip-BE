package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import fisa.woorizip.backend.member.domain.Member;

import java.time.LocalDate;

import static fisa.woorizip.backend.house.domain.HouseType.APARTMENT;
import static fisa.woorizip.backend.house.domain.HousingExpenses.JEONSE;

public class HouseFixture {
    private Long id;
    private Member member;
    private String name = "피사아파트";
    private String comment = "10층 중 3층.\n신축 풀옵션";
    private String address = "서울 마포구 월드컵북로 434 피사아파트";
    private HousingExpenses housingExpenses = JEONSE;
    private double latitude = 37.5815199;
    private double longitude = 126.8860032;
    private HouseType houseType = APARTMENT;
    private String gu = "마포구";
    private String dong = "상암동";
    private long deposit = 70000000;
    private long monthlyRentFee = 0;
    private long maintenanceFee = 50000;
    private int totalParkingSpaces = 110;
    private int householdParkingSpaces = 1;
    private String totalArea = "80.58";
    private String exclusiveArea = "73.54";
    private int rooms = 2;
    private int bathrooms = 1;
    private int floor = 3;
    private int totalFloors = 10;
    private LocalDate moveInDate = LocalDate.of(2025, 2, 1);
    private LocalDate approvalDate = LocalDate.of(2024, 11, 22);
    private String direction = "남향";

    public static HouseFixture builder() { return new HouseFixture(); }

    public HouseFixture id(Long id) {
        this.id = id;
        return this;
    }

    public HouseFixture member(Member member) {
        this.member = member;
        return this;
    }

    public HouseFixture name(String name) {
        this.name = name;
        return this;
    }

    public HouseFixture comment(String comment) {
        this.comment = comment;
        return this;
    }

    public HouseFixture address(String address) {
        this.address = address;
        return this;
    }

    public HouseFixture HousingExpenses(HousingExpenses housingExpenses) {
        this.housingExpenses = housingExpenses;
        return this;
    }

    public HouseFixture latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public HouseFixture longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public HouseFixture houseType(HouseType houseType) {
        this.houseType = houseType;
        return this;
    }

    public HouseFixture gu(String gu) {
        this.gu = gu;
        return this;
    }

    public HouseFixture dong(String dong) {
        this.dong = dong;
        return this;
    }

    public HouseFixture deposit(long deposit) {
        this.deposit = deposit;
        return this;
    }

    public HouseFixture monthlyRentFee(long monthlyRentFee) {
        this.monthlyRentFee = monthlyRentFee;
        return this;
    }

    public HouseFixture maintenanceFee(long maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
        return this;
    }

    public HouseFixture totalParkingSpaces(int totalParkingSpaces) {
        this.totalParkingSpaces = totalParkingSpaces;
        return this;
    }

    public HouseFixture householdParkingSpaces(int householdParkingSpaces) {
        this.householdParkingSpaces = householdParkingSpaces;
        return this;
    }

    public HouseFixture totalArea(String totalArea) {
        this.totalArea = totalArea;
        return this;
    }

    public HouseFixture exclusiveArea(String exclusiveArea) {
        this.exclusiveArea = exclusiveArea;
        return this;
    }

    public HouseFixture rooms(int rooms) {
        this.rooms = rooms;
        return this;
    }

    public HouseFixture bathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
        return this;
    }

    public HouseFixture floor(int floor) {
        this.floor = floor;
        return this;
    }

    public HouseFixture totalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
        return this;
    }

    public HouseFixture moveInDate(LocalDate moveInDate) {
        this.moveInDate = moveInDate;
        return this;
    }

    public HouseFixture approvalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
        return this;
    }

    public HouseFixture direction(String direction) {
        this.direction = direction;
        return this;
    }

    public House build() {
        return House.builder()
                .id(id)
                .member(member)
                .name(name)
                .comment(comment)
                .address(address)
                .housingExpenses(housingExpenses)
                .latitude(latitude)
                .longitude(longitude)
                .houseType(houseType)
                .gu(gu)
                .dong(dong)
                .deposit(deposit)
                .monthlyRentFee(monthlyRentFee)
                .maintenanceFee(maintenanceFee)
                .totalParkingSpaces(totalParkingSpaces)
                .householdParkingSpaces(householdParkingSpaces)
                .totalArea(totalArea)
                .exclusiveArea(exclusiveArea)
                .rooms(rooms)
                .bathrooms(bathrooms)
                .floor(floor)
                .totalFloors(totalFloors)
                .moveInDate(moveInDate)
                .approvalDate(approvalDate)
                .direction(direction)
                .build();
    }
}
