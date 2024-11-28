package fisa.woorizip.backend.loanchecklist.domain;

import lombok.Getter;

@Getter
public enum MarriageStatus {
    SINGLE("미혼"),
    MARRIED("기혼"),
    NEW_MARRIAGE("신혼");

    private final String name;

    MarriageStatus(String name) {
        this.name = name;
    }
}
