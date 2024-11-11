package fisa.woorizip.backend.house.dto.response;

import fisa.woorizip.backend.facility.domain.Facility;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FacilityResponse {
    private Long facilityId;
    private BigDecimal latitude;
    private BigDecimal longitude;

    private FacilityResponse(Long facilityId, BigDecimal latitude, BigDecimal longitude) {
        this.facilityId = facilityId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static FacilityResponse from(Facility facility) {
        return new FacilityResponse(
                facility.getId(), facility.getLatitude(), facility.getLongitude());
    }
}
