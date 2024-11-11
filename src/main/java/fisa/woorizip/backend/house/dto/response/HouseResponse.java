package fisa.woorizip.backend.house.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import fisa.woorizip.backend.house.dto.response.FacilityResponse;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class HouseResponse {
    private Long houseId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FacilityResponse> facilities;
}
