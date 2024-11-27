package fisa.woorizip.backend.member.domain;

import lombok.Getter;

@Getter
public enum LifeStage {
    UNI("대학생"),
    NEW_JOB("사회초년생"),
    NEW_WED("신혼"),
    CHILD_BABY("자녀_영유아"),
    CHILD_TEEN("자녀_의무교육"),
    CHILD_UNI("자녀_대학생"),
    GOLLIFE("중년기타"),
    NONE_LIFE_STAGE("해당없음");

    private final String description;

    LifeStage(String description) {
        this.description = description;
    }
}
