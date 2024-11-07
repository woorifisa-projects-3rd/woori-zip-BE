package fisa.woorizip.backend.facility.domain;

import lombok.Getter;

@Getter
public enum Category {
    FOOD("요식업");

    private final String name;

    Category(String name) {
        this.name = name;
    }
}
