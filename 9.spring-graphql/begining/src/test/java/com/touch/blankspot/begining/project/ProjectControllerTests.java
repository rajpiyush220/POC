package com.touch.blankspot.begining.project;


import com.touch.blankspot.begining.project.enums.ProjectStatus;
import com.touch.blankspot.begining.project.enums.ReleaseStatus;
import com.touch.blankspot.begining.project.type.Project;
import com.touch.blankspot.begining.project.type.Release;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

/**
 * GraphQL requests via {@link GraphQlTester}.
 */
@GraphQlTest(ProjectController.class)
public class ProjectControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private SpringProjectsClient projectsClient;

    private Project springFramework;

    private Release latestRelease;

    @BeforeEach
    void setup() {
        this.springFramework = new Project("spring-framework", "Spring Framework",
                "https://github.com/spring-projects/spring-framework", ProjectStatus.ACTIVE, List.of());
        this.latestRelease = new Release(this.springFramework, "5.3.0", ReleaseStatus.GENERAL_AVAILABILITY);
    }

    @Test
    void jsonPath() {

        given(this.projectsClient.fetchProject(eq("spring-framework")))
                .willReturn(this.springFramework);

        given(this.projectsClient.fetchProjectReleases(eq("spring-framework")))
                .willReturn(Collections.singletonList(this.latestRelease));

        this.graphQlTester.documentName("projectReleases")
                .variable("slug", "spring-framework")
                .execute()
                .path("project.releases[*].version")
                .entityList(String.class)
                .hasSize(1);

    }

    @Test
    void jsonContent() {
        given(this.projectsClient.fetchProject(eq("spring-framework"))).willReturn(this.springFramework);
        this.graphQlTester.documentName("projectRepositoryUrl")
                .variable("slug", "spring-framework")
                .execute()
                .path("project")
                .matchesJson("{\"repositoryUrl\":\"https://github.com/spring-projects/spring-framework\"}");
    }

    @Test
    void decodedResponse() {

        given(this.projectsClient.fetchProject(eq("spring-framework")))
                .willReturn(this.springFramework);

        given(this.projectsClient.fetchProjectReleases(eq("spring-framework")))
                .willReturn(Collections.singletonList(this.latestRelease));

        this.graphQlTester.documentName("projectReleases")
                .variable("slug", "spring-framework")
                .execute()
                .path("project")
                .entity(Project.class)
                .satisfies(project -> assertThat(project.getReleases()).hasSize(1));
    }

}