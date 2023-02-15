package com.touchblankspot.service.inventory.mapper;

import com.touchblankspot.service.inventory.data.model.Product;
import com.touchblankspot.service.inventory.type.product.management.ProductManagementRequestType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

  Product toEntity(ProductManagementRequestType request);
}
