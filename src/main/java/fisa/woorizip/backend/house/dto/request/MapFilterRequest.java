package fisa.woorizip.backend.house.dto.request;

import fisa.woorizip.backend.facility.domain.Category;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import fisa.woorizip.backend.house.dto.MapLevel;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

@Getter
public class MapFilterRequest {
    private MapLevel level;

    @Digits(integer = 3, fraction = 6)
    private BigDecimal southWestLatitude;

    @Digits(integer = 3, fraction = 6)
    private BigDecimal southWestLongitude;

    @Digits(integer = 3, fraction = 6)
    private BigDecimal northEastLatitude;

    @Digits(integer = 3, fraction = 6)
    private BigDecimal northEastLongitude;

    private HouseType houseType;
    private HousingExpenses housingExpenses;
    @PositiveOrZero private long minDeposit;
    @Positive private long maxDeposit;
    @PositiveOrZero private long minMonthlyRentFee;
    @Positive
    private long maxMonthlyRentFee;
    @PositiveOrZero
    private long minMaintenanceFee;
    @Positive private long maxMaintenanceFee;
    private Category category;
    private int walking;
    private int facilityCount;
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
            BigDecimal southWestLatitude,
            BigDecimal southWestLongitude,
            BigDecimal northEastLatitude,
            BigDecimal northEastLongitude,
            String houseType,
            String housingExpenses,
            long minDeposit,
            long maxDeposit,
            long minMonthlyRentFee,
            long maxMonthlyRentFee,
            long minMaintenanceFee,
            long maxMaintenanceFee,
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
        this.houseType = HouseType.from(houseType);
        this.housingExpenses = HousingExpenses.from(housingExpenses);
        this.minDeposit = minDeposit;
        this.maxDeposit = maxDeposit;
        this.minMonthlyRentFee = minMonthlyRentFee;
        this.maxMonthlyRentFee = maxMonthlyRentFee;
        this.minMaintenanceFee = minMaintenanceFee;
        this.maxMaintenanceFee = maxMaintenanceFee;
        this.category = Category.from(category);
        this.walking = walking == null ? 0 : walking;
        this.facilityCount = facilityCount == null ? 0 : facilityCount;
        this.gu = gu == null ? "" : gu;
        this.dong = dong == null ? "" : dong;
    }
}
