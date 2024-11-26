package fisa.woorizip.backend.member.domain;

import static fisa.woorizip.backend.member.MemberErrorCode.MEMBERSHIP_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Membership {
    VVIP("VVIP", 21),
    VIP("VIP", 22),
    PLATINUM("플레티넘", 23),
    GOLD("골드", 24),
    NONE_MEMBERSHIP("해당없음", 25);

    private String name;
    private int value;

    Membership(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static Membership from(int value) {
        return Arrays.stream(Membership.values())
                .filter(membership -> membership.value == value)
                .findAny()
                .orElseThrow(() -> new WooriZipException(MEMBERSHIP_NOT_FOUND));
    }
}
