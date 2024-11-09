package fisa.woorizip.backend.house.dto;

import fisa.woorizip.backend.common.exception.WooriZipException;
import lombok.Getter;

import java.util.Arrays;

import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_ADDRESS_TYPE_NOT_FOUND;

@Getter
public enum HouseAddressType {
    GU("구"),
    DONG("동");

    private final String name;

    HouseAddressType(String name) {
        this.name = name;
    }

    public static HouseAddressType from(final String name) {
        return Arrays.stream(HouseAddressType.values())
                .filter(addressType -> addressType.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new WooriZipException(HOUSE_ADDRESS_TYPE_NOT_FOUND));
    }
}
