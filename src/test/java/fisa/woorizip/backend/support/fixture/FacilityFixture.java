package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.facility.domain.Category;
import fisa.woorizip.backend.facility.domain.Facility;

import java.math.BigDecimal;

public class FacilityFixture {

    private Long id;
    private Category category = Category.FOOD;
    private String name = "이가네 짜글이";
    private String gu = "마포구";
    private String dong = "상암동";
    private double latitude = 37.5813506;
    private double longitude = 126.886193;

    public static FacilityFixture builder() {
        return new FacilityFixture();
    }

    public FacilityFixture id(Long id) {
        this.id = id;
        return this;
    }

    public FacilityFixture category(Category category) {
        this.category = category;
        return this;
    }

    public FacilityFixture name(String name) {
        this.name = name;
        return this;
    }

    public FacilityFixture gu(String gu) {
        this.gu = gu;
        return this;
    }

    public FacilityFixture dong(String dong) {
        this.dong = dong;
        return this;
    }

    public FacilityFixture latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public FacilityFixture longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Facility build() {
        return Facility.builder()
                .id(id)
                .category(category)
                .name(name)
                .gu(gu)
                .dong(dong)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
