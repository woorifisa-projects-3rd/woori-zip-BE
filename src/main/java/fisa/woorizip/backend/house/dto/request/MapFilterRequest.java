package fisa.woorizip.backend.house.dto.request;

import fisa.woorizip.backend.facility.domain.Category;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import fisa.woorizip.backend.house.dto.MapLevel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.beans.ConstructorProperties;

import static fisa.woorizip.backend.house.domain.HouseType.ALL;
import static fisa.woorizip.backend.house.domain.HousingExpenses.ANY;
import static fisa.woorizip.backend.facility.domain.Category.NONE;

@Getter
public class MapFilterRequest {
    private MapLevel level;

    @NotNull private double southWestLatitude;

    @NotNull private double southWestLongitude;

    @NotNull private double northEastLatitude;

    @NotNull private double northEastLongitude;

    private HouseType houseType;
    private HousingExpenses housingExpenses;
    @PositiveOrZero private long minDeposit;
    @Positive private long maxDeposit;
    @PositiveOrZero private long minMonthlyRentFee;
    @Positive private long maxMonthlyRentFee;
    @PositiveOrZero private long minMaintenanceFee;
    @Positive private long maxMaintenanceFee;
    private Category category;
    private Integer walking;
    private Integer facilityCount;
    private String gu;
    private String dong;

    @ConstructorProperties({
        "level",
        "southWestLatitude",
        "southWestLongitude",
        "northEastLatitude",
        "northEastLongitude",
        "houseType",
        "housingExpenses",
        "minDeposit",
        "maxDeposit",
        "minMonthlyRentFee",
        "maxMonthlyRentFee",
        "minMaintenanceFee",
        "maxMaintenanceFee",
        "category",
        "walking",
        "facilityCount",
        "gu",
        "dong"
    })
    private MapFilterRequest(
            int level,
            double southWestLatitude,
            double southWestLongitude,
            double northEastLatitude,
            double northEastLongitude,
            String houseType,
            String housingExpenses,
            Long minDeposit,
            Long maxDeposit,
            Long minMonthlyRentFee,
            Long maxMonthlyRentFee,
            Long minMaintenanceFee,
            Long maxMaintenanceFee,
            String category,
            Integer walking,
            Integer facilityCount,
            String gu,
            String dong) {
        this.level = MapLevel.from(level);
        this.southWestLatitude = southWestLatitude;
        this.southWestLongitude = southWestLongitude;
        this.northEastLatitude = northEastLatitude;
        this.northEastLongitude = northEastLongitude;
        this.houseType = houseType == null ? ALL : HouseType.from(houseType);
        this.housingExpenses =
                housingExpenses == null ? ANY : HousingExpenses.from(housingExpenses);
        this.minDeposit = minDeposit == null ? 0 : minDeposit;
        this.maxDeposit = maxDeposit == null ? Long.MAX_VALUE : maxDeposit;
        this.minMonthlyRentFee = minMonthlyRentFee == null ? 0 : minMonthlyRentFee;
        this.maxMonthlyRentFee = maxMonthlyRentFee == null ? Long.MAX_VALUE : maxMonthlyRentFee;
        this.minMaintenanceFee = minMaintenanceFee == null ? 0 : minMaintenanceFee;
        this.maxMaintenanceFee = maxMaintenanceFee == null ? Long.MAX_VALUE : maxMaintenanceFee;
        this.category = category == null ? NONE : Category.from(category);
        this.walking = walking;
        this.facilityCount = facilityCount;
        this.gu = gu;
        this.dong = dong;
    }

    public static MapFilterRequest of(
            int level,
            double southWestLatitude,
            double southWestLongitude,
            double northEastLatitude,
            double northEastLongitude) {
        return new MapFilterRequest(
                level,
                southWestLatitude,
                southWestLongitude,
                northEastLatitude,
                northEastLongitude,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static MapFilterRequest of(
            int level,
            double southWestLatitude,
            double southWestLongitude,
            double northEastLatitude,
            double northEastLongitude,
            String category,
            Integer walking,
            Integer facilityCount) {
        return new MapFilterRequest(
                level,
                southWestLatitude,
                southWestLongitude,
                northEastLatitude,
                northEastLongitude,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                category,
                walking,
                facilityCount,
                null,
                null);
    }
}
