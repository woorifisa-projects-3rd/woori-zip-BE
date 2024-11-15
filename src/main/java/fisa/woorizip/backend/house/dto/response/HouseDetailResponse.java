package fisa.woorizip.backend.house.dto.response;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class HouseDetailResponse {
    private final Long id;
    private final String name;
    private final String address;
    private final String comment;
    private final HousingExpenses housingExpenses;
    private final double latitude;
    private final double longitude;
    private final HouseType houseType;
    private final String gu;
    private final String dong;
    private final long deposit;
    private final long monthlyRentFee;
    private final long maintenanceFee;
    private final int totalParkingSpaces;
    private final int householdParkingSpaces;
    private final String totalArea;
    private final String exclusiveArea;
    private final int rooms;
    private final int bathrooms;
    private final int floor;
    private final int totalFloors;
    private final LocalDate moveInDate;
    private final LocalDate approvalDate;
    private final String direction;
    private final List<String> imageUrls;

    public static HouseDetailResponse of(House house, List<String> imageUrls) {
        return HouseDetailResponse.builder()
                .id(house.getId())
                .name(house.getName())
                .address(house.getAddress())
                .comment(house.getComment())
                .housingExpenses(house.getHousingExpenses())
                .latitude(house.getLatitude())
                .longitude(house.getLongitude())
                .houseType(house.getHouseType())
                .gu(house.getGu())
                .dong(house.getDong())
                .deposit(house.getDeposit())
                .monthlyRentFee(house.getMonthlyRentFee())
                .maintenanceFee(house.getMaintenanceFee())
                .totalParkingSpaces(house.getTotalParkingSpaces())
                .householdParkingSpaces(house.getHouseholdParkingSpaces())
                .totalArea(house.getTotalArea())
                .exclusiveArea(house.getExclusiveArea())
                .rooms(house.getRooms())
                .bathrooms(house.getBathrooms())
                .floor(house.getFloor())
                .totalFloors(house.getTotalFloors())
                .moveInDate(house.getMoveInDate())
                .approvalDate(house.getApprovalDate())
                .direction(house.getDirection())
                .imageUrls(imageUrls)
                .build();
    }
}