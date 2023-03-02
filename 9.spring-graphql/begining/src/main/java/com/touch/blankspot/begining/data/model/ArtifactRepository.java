package com.touch.blankspot.begining.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtifactRepository {

    @Id
    @NonNull
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String url;

    private boolean snapshotsEnabled;

}
