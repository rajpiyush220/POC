package com.touch.blankspot.begining.project.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum ProjectStatus {

    ACTIVE, COMMUNITY, INCUBATING, ATTIC;

    @JsonCreator
    public static ProjectStatus fromName(String name) {
        return Arrays.stream(ProjectStatus.values())
                .filter(type -> type.name().equals(name))
                .findFirst()
                .orElse(ProjectStatus.ACTIVE);
    }

}
