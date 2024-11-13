package fisa.woorizip.backend.house.dto.result;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseContentResult {
    private Long houseId;
    private String housingExpenses;
    private long deposit;
    private long monthlyRentFee;
    private String houseType;
    private String gu;
    private String dong;
    private long maintenanceFee;
    private String comment;

    public static HouseContentResult init(House house) {
        return new HouseContentResult(
                house.getId(),
                house.getHousingExpenses().getName(),
                house.getDeposit(),
                house.getMonthlyRentFee(),
                house.getHouseType().getName(),
                house.getGu(),
                house.getDong(),
                house.getMaintenanceFee(),
                house.getComment());
    }
}
