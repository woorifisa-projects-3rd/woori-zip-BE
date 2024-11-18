package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.facility.domain.Facility;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.housefacilityrelation.domain.HouseFacilityRelation;

public class HouseFacilityRelationFixture {
    private Long id;
    private House house;
    private Facility facility;
    private String distance = "100";
    private int walking = 10;

    public static HouseFacilityRelationFixture builder() {
        return new HouseFacilityRelationFixture();
    }

    public HouseFacilityRelationFixture id(Long id) {
        this.id = id;
        return this;
    }

    public HouseFacilityRelationFixture house(House house) {
        this.house = house;
        return this;
    }

    public HouseFacilityRelationFixture facility(Facility facility) {
        this.facility = facility;
        return this;
    }

    public HouseFacilityRelationFixture distance(String distance) {
        this.distance = distance;
        return this;
    }

    public HouseFacilityRelationFixture walking(int walking) {
        this.walking = walking;
        return this;
    }

    public HouseFacilityRelation build() {
        return HouseFacilityRelation.builder()
                .id(id)
                .house(house)
                .facility(facility)
                .distance(distance)
                .walking(walking)
                .build();
    }
}
