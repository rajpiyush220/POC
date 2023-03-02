package com.touch.blankspot.begining.project.type;

import com.touch.blankspot.begining.project.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Project {
    private String slug;

    private String name;

    private String repositoryUrl;

    private ProjectStatus status;

    private List<Release> releases;
}
