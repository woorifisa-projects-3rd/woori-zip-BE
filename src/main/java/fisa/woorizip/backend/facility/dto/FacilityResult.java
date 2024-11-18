package fisa.woorizip.backend.facility.dto;

import fisa.woorizip.backend.facility.domain.Facility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityResult {
    private Long facilityId;
    private double latitude;
    private double longitude;
}
