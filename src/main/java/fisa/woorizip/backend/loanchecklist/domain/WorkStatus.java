package fisa.woorizip.backend.loanchecklist.domain;

import lombok.Getter;

@Getter
public enum WorkStatus {
    UNEMPLOYED("무직"),
    EMPLOYED("직장인"),
    SELF_EMPLOYED("자영업자");

    private final String name;

    WorkStatus(String name) {
        this.name = name;
    }
}
