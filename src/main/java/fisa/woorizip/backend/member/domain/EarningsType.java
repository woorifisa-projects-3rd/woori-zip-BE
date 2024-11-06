package fisa.woorizip.backend.member.domain;

import lombok.Getter;

@Getter
public enum EarningsType {
    EARNED("근로 소득"),
    BUSINESS("사업 소득"),
    OTHER("기타 소득"),
    NO("소득 없음");

    private final String name;

    EarningsType(String name) { this.name = name; }
}
