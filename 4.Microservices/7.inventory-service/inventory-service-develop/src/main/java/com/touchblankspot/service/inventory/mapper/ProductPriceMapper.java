package com.touchblankspot.service.inventory.mapper;

import com.touchblankspot.service.inventory.data.model.ProductPrice;
import com.touchblankspot.service.inventory.type.product.price.ProductPriceRequestType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductPriceMapper {

  ProductPrice toEntity(ProductPriceRequestType request);
}
