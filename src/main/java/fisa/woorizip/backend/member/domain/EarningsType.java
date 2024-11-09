package fisa.woorizip.backend.member.domain;

import static fisa.woorizip.backend.member.MemberErrorCode.EARNINGS_TYPE_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EarningsType {
    EARNED("근로 소득"),
    BUSINESS("사업 소득"),
    OTHER("기타 소득"),
    NO("소득 없음");

    private final String name;

    EarningsType(String name) {
        this.name = name;
    }

    public static EarningsType from(final String name) {
        return Arrays.stream(EarningsType.values())
                .filter(type -> type.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new WooriZipException(EARNINGS_TYPE_NOT_FOUND));
    }
}
