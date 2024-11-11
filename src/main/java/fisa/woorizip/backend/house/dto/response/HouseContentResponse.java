package fisa.woorizip.backend.house.dto.response;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import lombok.Getter;

@Getter
public class HouseContentResponse {
    private Long houseId;
    private String housingExpenses;
    private long deposit;
    private long monthlyRentFee;
    private String houseType;
    private String gu;
    private String dong;
    private long maintenanceFee;
    private String comment;

    private HouseContentResponse(
            Long houseId,
            HousingExpenses housingExpenses,
            long deposit,
            long monthlyRentFee,
            HouseType houseType,
            String gu,
            String dong,
            long maintenanceFee,
            String comment) {
        this.houseId = houseId;
        this.housingExpenses = housingExpenses.getName();
        this.deposit = deposit;
        this.monthlyRentFee = monthlyRentFee;
        this.houseType = houseType.getName();
        this.gu = gu;
        this.dong = dong;
        this.maintenanceFee = maintenanceFee;
        this.comment = comment;
    }

    public static HouseContentResponse from(House house) {
        return new HouseContentResponse(
                house.getId(),
                house.getHousingExpenses(),
                house.getDeposit(),
                house.getMonthlyRentFee(),
                house.getHouseType(),
                house.getGu(),
                house.getDong(),
                house.getMaintenanceFee(),
                house.getComment());
    }
}
