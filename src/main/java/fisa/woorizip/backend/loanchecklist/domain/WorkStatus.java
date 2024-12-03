package fisa.woorizip.backend.loanchecklist.domain;

import lombok.Getter;

@Getter
public enum WorkStatus {
    UNEMPLOYED("비작징인"),
    EMPLOYED("직장인"),
    ALL("전체");

    private final String name;

    WorkStatus(String name) {
        this.name = name;
    }
}
