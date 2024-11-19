package fisa.woorizip.backend.house.dto.request;

import static fisa.woorizip.backend.facility.domain.Category.NONE;
import static fisa.woorizip.backend.house.domain.HouseType.ALL;
import static fisa.woorizip.backend.house.domain.HousingExpenses.ANY;
import static java.util.Objects.isNull;

import fisa.woorizip.backend.facility.domain.Category;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import fisa.woorizip.backend.house.dto.MapLevel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class MapFilterRequest {
    private final MapLevel level;

    @NotNull final private double southWestLatitude;

    @NotNull final private double southWestLongitude;

    @NotNull final private double northEastLatitude;

    @NotNull final private double northEastLongitude;

    private final HouseType houseType;
    private final HousingExpenses housingExpenses;
    @PositiveOrZero final private long minDeposit;
    @Positive final private long maxDeposit;
    @PositiveOrZero final private long minMonthlyRentFee;
    @Positive final private long maxMonthlyRentFee;
    @PositiveOrZero final private long minMaintenanceFee;
    @Positive final private long maxMaintenanceFee;
    private final Category category;
    private final Integer walking;
    private final Integer facilityCount;
    private final String gu;
    private final String dong;

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
        this.houseType = isNull(houseType) ? ALL : HouseType.from(houseType);
        this.housingExpenses =
                isNull(housingExpenses) ? ANY : HousingExpenses.from(housingExpenses);
        this.minDeposit = isNull(minDeposit) ? 0 : minDeposit;
        this.maxDeposit = isNull(maxDeposit) ? Long.MAX_VALUE : maxDeposit;
        this.minMonthlyRentFee = isNull(minMonthlyRentFee) ? 0 : minMonthlyRentFee;
        this.maxMonthlyRentFee = isNull(maxMonthlyRentFee) ? Long.MAX_VALUE : maxMonthlyRentFee;
        this.minMaintenanceFee = isNull(minMaintenanceFee) ? 0 : minMaintenanceFee;
        this.maxMaintenanceFee = isNull(maxMaintenanceFee) ? Long.MAX_VALUE : maxMaintenanceFee;
        this.category = isNull(category) ? NONE : Category.from(category);
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

    public static MapFilterRequest of(
            int level,
            double southWestLatitude,
            double southWestLongitude,
            double northEastLatitude,
            double northEastLongitude,
            String category,
            Integer walking,
            Integer facilityCount,
            String gu,
            String dong) {
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
                gu,
                dong);
    }
}
