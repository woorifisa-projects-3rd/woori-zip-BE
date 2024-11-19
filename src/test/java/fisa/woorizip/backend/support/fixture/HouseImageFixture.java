package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.houseimage.domain.HouseImage;

public class HouseImageFixture {

    private Long id;
    private String url = "https://example.com/sample-image.jpg";
    private int orderIndex = 0;

    public static HouseImageFixture builder() {
        return new HouseImageFixture();
    }

    public HouseImageFixture url(String url) {
        this.url = url;
        return this;
    }

    public HouseImageFixture orderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
        return this;
    }

    public HouseImage build(House house) {
        return HouseImage.builder().house(house).url(url).orderIndex(orderIndex).build();
    }
}
