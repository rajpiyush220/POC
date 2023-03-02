package com.touch.blankspot.begining.data.repository;

import com.touch.blankspot.begining.data.model.ArtifactRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ArtifactRepositoriesInitializer implements ApplicationRunner {

    private final ArtifactRepositories repositories;

    public ArtifactRepositoriesInitializer(ArtifactRepositories repositories) {
        this.repositories = repositories;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<ArtifactRepository> repositoryList = Arrays.asList(
                ArtifactRepository.builder().id("spring-releases").name("Spring Releases")
                        .url("https://repo.spring.io/libs-releases").build(),
                ArtifactRepository.builder().id("spring-milestones").name("Spring Milestones")
                        .url("https://repo.spring.io/libs-milestones").build(),
                ArtifactRepository.builder().id("spring-snapshots").name("Spring Snapshots")
                        .url("https://repo.spring.io/libs-snapshots").build());
        repositories.saveAll(repositoryList);
    }
}
