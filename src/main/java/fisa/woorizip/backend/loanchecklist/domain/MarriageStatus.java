package fisa.woorizip.backend.loanchecklist.domain;

import static fisa.woorizip.backend.loanchecklist.LoanChecklistErrorCode.MARRIAGE_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MarriageStatus {
    SINGLE("미혼"),
    MARRIED("기혼"),
    NEW_MARRIAGE("신혼"),
    NONE_MARRIAGE("해당없음");

    private final String name;

    MarriageStatus(String name) {
        this.name = name;
    }

    public static MarriageStatus from(String name) {
        return Arrays.stream(MarriageStatus.values())
                .filter(marriage -> marriage.name().equals(name))
                .findAny()
                .orElseThrow(() -> new WooriZipException(MARRIAGE_NOT_FOUND));
    }
}
