package fisa.woorizip.backend.house.domain;

import lombok.Getter;

@Getter
public enum HouseType {
    ONE_ROOM("원룸"),
    APARTMENT("아파트");

    private final String name;

    HouseType(String name) {
        this.name = name;
    }
}
