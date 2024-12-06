package fisa.woorizip.backend.loanchecklist.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum WorkTerm {
    NONE_TERM("해당없음"),
    ONE_YEAR("1년 이상"),
    THREE_MONTH("3개월 이상");

    private final String name;

    WorkTerm(String name) {
        this.name = name;
    }

    public static WorkTerm from(String name) {
        return Arrays.stream(WorkTerm.values())
                .filter(workTerm -> workTerm.name().equals(name))
                .findAny()
                .orElse(NONE_TERM);
    }
}
