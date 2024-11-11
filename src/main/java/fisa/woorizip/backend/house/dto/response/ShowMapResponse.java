package fisa.woorizip.backend.house.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import fisa.woorizip.backend.house.dto.HouseAddressType;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
public class ShowMapResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String addressType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String addressName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int count;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<HouseResponse> houses;

    private Slice<HouseContentResponse> houseContents;

    private ShowMapResponse(
            HouseAddressType addressType,
            String addressName,
            int count,
            Slice<HouseContentResponse> houseContents) {
        this.addressType = addressType.getName();
        this.addressName = addressName;
        this.count = count;
        this.houseContents = houseContents;
    }

    private ShowMapResponse(List<HouseResponse> houses, Slice<HouseContentResponse> houseContents) {
        this.houses = houses;
        this.houseContents = houseContents;
    }

    public static ShowMapResponse of(
            HouseAddressType addressType,
            String addressName,
            int count,
            Slice<HouseContentResponse> houses) {
        return new ShowMapResponse(addressType, addressName, count, houses);
    }

    public static ShowMapResponse of(
            List<HouseResponse> houses, Slice<HouseContentResponse> houseContents) {
        return new ShowMapResponse(houses, houseContents);
    }
}
