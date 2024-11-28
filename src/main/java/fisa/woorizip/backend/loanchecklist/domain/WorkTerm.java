package fisa.woorizip.backend.loanchecklist.domain;

import lombok.Getter;

@Getter
public enum WorkTerm {
    NONE_TERM("해당없음"),
    ONE_YEAR("1년 이상"),
    THREE_MONTH("3개월 이상");

    private final String name;

    WorkTerm(String name) {
        this.name = name;
    }
}
