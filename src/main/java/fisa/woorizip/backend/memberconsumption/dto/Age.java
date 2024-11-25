package fisa.woorizip.backend.memberconsumption.dto;

import static fisa.woorizip.backend.member.MemberErrorCode.AGE_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Age {
    TWENTY(20, 24),
    TWENTY_FIVE(25, 29),
    THIRTY(30, 34),
    THIRTY_FIVE(35, 39);

    private int minAge;
    private int maxAge;

    Age(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public static Age from(int age) {
        return Arrays.stream(Age.values())
                .filter(ageType -> ageType.minAge <= age && ageType.maxAge >= age)
                .findAny()
                .orElseThrow(() -> new WooriZipException(AGE_NOT_FOUND));
    }
}
