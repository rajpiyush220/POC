package com.touch.blankspot.progress.mapper;


import com.touch.blankspot.progress.contract.apitype.ApiTaskCategoryRequest;
import com.touch.blankspot.progress.contract.apitype.ApiTaskCategoryResponse;
import com.touch.blankspot.progress.portal.data.model.TaskCategory;
import com.touch.blankspot.progress.portal.service.TaskCategoryService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                // add if it is using any other mapper
        }
)
public interface TaskCategoryMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "organizationId", source = "organizationId")
    TaskCategory toEntity(ApiTaskCategoryRequest request);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "organizationId", source = "organizationId")
    ApiTaskCategoryResponse toApi(TaskCategory category);

}
