package com.touch.blankspot.begining.project.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum ReleaseStatus {

    GENERAL_AVAILABILITY, MILESTONE, SNAPSHOT;

    @JsonCreator
    public static ReleaseStatus fromName(String name) {
        return Arrays.stream(ReleaseStatus.values())
                .filter(type -> type.name().equals(name))
                .findFirst()
                .orElse(ReleaseStatus.GENERAL_AVAILABILITY);
    }
}