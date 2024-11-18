package fisa.woorizip.backend.house.dto.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import fisa.woorizip.backend.facility.domain.Facility;
import fisa.woorizip.backend.facility.dto.FacilityResult;
import fisa.woorizip.backend.house.domain.House;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseResult {
    private Long houseId;
    private double latitude;
    private double longitude;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FacilityResult> facilities;

    public static HouseResult init(House house) {
        return new HouseResult(house.getId(), house.getLatitude(), house.getLongitude(), null);
    }

    public static HouseResult init(House house, List<Facility> facilities) {
        return new HouseResult(
                house.getId(),
                house.getLatitude(),
                house.getLongitude(),
                facilities.stream().map(FacilityResult::init).toList());
    }
}
