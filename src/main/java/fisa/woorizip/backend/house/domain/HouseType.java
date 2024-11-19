package fisa.woorizip.backend.house.domain;

import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_TYPE_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum HouseType {
    ONE_ROOM("원/투룸"),
    OFFICE_TEL("오피스텔"),
    VILLA("주택/빌라"),
    APARTMENT("아파트"),
    ALL("전체");

    private final String name;

    HouseType(String name) {
        this.name = name;
    }

    public static HouseType from(String name) {
        return Arrays.stream(HouseType.values())
                .filter(houseType -> houseType.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new WooriZipException(HOUSE_TYPE_NOT_FOUND));
    }
}
