package com.touch.blankspot.progress.portal.controller;

import com.touch.blankspot.common.annotation.ProgressPortalRestController;
import com.touch.blankspot.common.error.ApiResponse;
import com.touch.blankspot.progress.contract.apitype.ApiTaskCategoryRequest;
import com.touch.blankspot.progress.contract.apitype.ApiTaskCategoryResponse;
import com.touch.blankspot.progress.mapper.TaskCategoryMapper;
import com.touch.blankspot.progress.portal.data.model.TaskCategory;
import com.touch.blankspot.progress.portal.service.TaskCategoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ProgressPortalRestController
//@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ProgressPortalTaskCategoryController {

    @NonNull
    private final TaskCategoryService taskCategoryService;

    @NonNull
    private final TaskCategoryMapper taskCategoryMapper;

    @PostMapping("/task/category")
    public ResponseEntity<ApiResponse<ApiTaskCategoryResponse>> add(@RequestBody ApiTaskCategoryRequest request) {
        TaskCategory category = taskCategoryService.save(taskCategoryMapper.toEntity(request));
        log.error(" TaskCategory saved " + category);
        return ResponseEntity.ok().body(new ApiResponse<ApiTaskCategoryResponse>(taskCategoryMapper.toApi(category)));
    }

}
