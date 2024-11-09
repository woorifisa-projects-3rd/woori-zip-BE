package fisa.woorizip.backend.house.dto;

import fisa.woorizip.backend.common.exception.WooriZipException;
import lombok.Getter;

import java.util.Arrays;

import static fisa.woorizip.backend.house.HouseErrorCode.MAP_LEVEL_NOT_FOUND;

@Getter
public enum MapLevel {
    HIGH(9, 10),
    MID(6, 8),
    LOW(1, 5);

    private final int minLevel;
    private final int maxLevel;

    MapLevel(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    public static MapLevel from(final int level) {
        return Arrays.stream(MapLevel.values())
                .filter(
                        mapLevel ->
                                mapLevel.getMinLevel() <= level && mapLevel.getMaxLevel() >= level)
                .findAny()
                .orElseThrow(() -> new WooriZipException(MAP_LEVEL_NOT_FOUND));
    }
}
