package fisa.woorizip.backend.facility.dto;

import fisa.woorizip.backend.facility.domain.Facility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityResult {
    private Long facilityId;
    private double latitude;
    private double longitude;

    public static FacilityResult init(Facility facility) {
        return new FacilityResult(
                facility.getId(), facility.getLatitude(), facility.getLongitude());
    }
}
