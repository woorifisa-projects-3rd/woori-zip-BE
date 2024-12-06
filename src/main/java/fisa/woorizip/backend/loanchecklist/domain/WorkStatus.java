package fisa.woorizip.backend.loanchecklist.domain;

import static fisa.woorizip.backend.loanchecklist.LoanChecklistErrorCode.WORK_STATUS_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum WorkStatus {
    UNEMPLOYED("무직"),
    EMPLOYED("직장인"),
    NONE_WORK_STATUS("해당없음");

    private final String name;

    WorkStatus(String name) {
        this.name = name;
    }

    public static WorkStatus from(String name) {
        return Arrays.stream(WorkStatus.values())
                .filter(workStatus -> workStatus.name().equals(name))
                .findAny()
                .orElseThrow(() -> new WooriZipException(WORK_STATUS_NOT_FOUND));
    }
}
