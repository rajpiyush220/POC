package com.touch.blankspot.progress.portal.data.repository;

import com.touch.blankspot.progress.portal.data.model.TaskAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskActionRepository extends CrudRepository<TaskAction, UUID> {
}
