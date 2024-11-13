package fisa.woorizip.backend.facility.dto;

import fisa.woorizip.backend.facility.domain.Facility;
import lombok.Getter;

@Getter
public class FacilityResponse {
    private Long facilityId;
    private double latitude;
    private double longitude;

    private FacilityResponse(Long facilityId, double latitude, double longitude) {
        this.facilityId = facilityId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static FacilityResponse from(Facility facility) {
        return new FacilityResponse(
                facility.getId(), facility.getLatitude(), facility.getLongitude());
    }
}
