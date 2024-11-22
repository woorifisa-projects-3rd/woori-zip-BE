package fisa.woorizip.backend.member.domain;

import fisa.woorizip.backend.common.exception.WooriZipException;
import lombok.Getter;

import java.util.Arrays;

import static fisa.woorizip.backend.member.MemberErrorCode.GENDER_NOT_FOUND;

@Getter
public enum Gender {
    MALE("남성", 1),
    FEMALE("여성", 2);

    private final String name;
    private final int value;

    Gender(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static Gender from(int value) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.getValue() == value)
                .findAny()
                .orElseThrow(() -> new WooriZipException(GENDER_NOT_FOUND));
    }
}
