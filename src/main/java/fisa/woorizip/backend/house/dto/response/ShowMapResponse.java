package fisa.woorizip.backend.house.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import fisa.woorizip.backend.house.dto.HouseAddressType;
import fisa.woorizip.backend.house.dto.result.HouseContentResult;
import fisa.woorizip.backend.house.dto.result.HouseCountResult;
import fisa.woorizip.backend.house.dto.result.HouseResult;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ShowMapResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String houseAddressType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<HouseCountResult> counts;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<HouseResult> houses;

    private List<HouseContentResult> houseContents;

    private ShowMapResponse(
            String houseAddressType,
            List<HouseCountResult> counts,
            List<HouseContentResult> houseContents) {
        this.houseAddressType = houseAddressType;
        this.counts = counts;
        this.houseContents = houseContents;
    }

    private ShowMapResponse(List<HouseResult> houses, List<HouseContentResult> houseContents) {
        this.houses = houses;
        this.houseContents = houseContents;
    }

    public static ShowMapResponse of(
            HouseAddressType houseAddressType,
            List<HouseCountResult> counts,
            List<HouseContentResult> houseContents) {
        return new ShowMapResponse(houseAddressType.getName(), counts, houseContents);
    }

    public static ShowMapResponse of(
            List<HouseResult> houses, List<HouseContentResult> houseContents) {
        return new ShowMapResponse(houses, houseContents);
    }
}
