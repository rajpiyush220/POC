package com.touch.blankspot.begining.project.type;

import com.touch.blankspot.begining.project.enums.ReleaseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Release {
    private String version;

    private ReleaseStatus status;

    private String referenceDocUrl;

    private String apiDocUrl;

    private boolean current;

    public Release() {
    }

    public Release(Project project, String version, ReleaseStatus status) {
        this.version = version;
        this.status = status;
        this.apiDocUrl = String.format("https://docs.spring.io/%s/docs/%s/javadoc-api/", project.getSlug(), version);
        this.referenceDocUrl = String.format("https://docs.spring.io/%s/docs/%s/reference/html/", project.getSlug(), version);
    }
}
