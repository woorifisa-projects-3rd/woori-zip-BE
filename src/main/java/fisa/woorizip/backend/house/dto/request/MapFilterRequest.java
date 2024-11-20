package fisa.woorizip.backend.house.dto.request;

import static fisa.woorizip.backend.facility.domain.Category.NONE;
import static fisa.woorizip.backend.house.domain.HouseType.ALL;
import static fisa.woorizip.backend.house.domain.HousingExpenses.ANY;
import static java.util.Objects.isNull;

import fisa.woorizip.backend.facility.domain.Category;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import fisa.woorizip.backend.house.dto.MapLevel;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class MapFilterRequest {
    private final MapLevel level;

    @NotNull private final double southWestLatitude;

    @NotNull private final double southWestLongitude;

    @NotNull private final double northEastLatitude;

    @NotNull private final double northEastLongitude;

    private final HouseType houseType;
    private final HousingExpenses housingExpenses;
    @PositiveOrZero private final long minDeposit;
    @Positive private final long maxDeposit;
    @PositiveOrZero private final long minMonthlyRentFee;
    @Positive private final long maxMonthlyRentFee;
    @PositiveOrZero private final long minMaintenanceFee;
    @Positive private final long maxMaintenanceFee;
    private final Category category;
    private final Integer walking;
    private final Integer facilityCount;
    private final String gu;
    private final String dong;

    @AssertTrue(message = "주소(구, 동)는 함께 입력되어야 합니다.")
    public boolean isGuAndDongValid() {
        return isNull(gu) == isNull(dong);
    }

    @AssertTrue(message = "카테고리가 존재하는 경우 도보 시간과 시설 개수가 입력되어야 합니다.")
    public boolean isCategoryValid() {
        return isNull(walking) == isNull(facilityCount) && isNull(walking) == (category == NONE);
    }

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
