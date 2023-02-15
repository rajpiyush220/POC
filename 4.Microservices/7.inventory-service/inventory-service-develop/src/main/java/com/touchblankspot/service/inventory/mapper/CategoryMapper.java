package com.touchblankspot.service.inventory.mapper;

import com.touchblankspot.service.inventory.data.model.Category;
import com.touchblankspot.service.inventory.type.product.category.CategoryRequestType;
import com.touchblankspot.service.inventory.type.product.category.CategoryResponseType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

  Category toEntity(CategoryRequestType request);

  CategoryResponseType toResponse(Category productCategory);
}
