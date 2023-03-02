package com.touch.blankspot.progress.portal.data.model;

import com.touch.blankspot.common.model.embedded.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "task_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCategory extends Immutable {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "organization_id")
    private UUID organizationId;

    @OneToMany(orphanRemoval = true, targetEntity = Task.class, mappedBy = "category")
    private List<Task> tasks = new ArrayList<>();
}
