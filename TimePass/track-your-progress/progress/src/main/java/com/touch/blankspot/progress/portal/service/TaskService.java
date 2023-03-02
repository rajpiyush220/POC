package com.touch.blankspot.progress.portal.service;

import com.touch.blankspot.progress.portal.data.repository.TaskRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskService {

    @NonNull
    private final TaskRepository taskRepository;
}
