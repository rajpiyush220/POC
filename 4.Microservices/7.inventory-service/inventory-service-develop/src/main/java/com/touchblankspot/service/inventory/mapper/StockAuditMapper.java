package com.touchblankspot.service.inventory.mapper;

import com.touchblankspot.service.inventory.data.model.StockAudit;
import com.touchblankspot.service.inventory.service.ProductService;
import com.touchblankspot.service.inventory.type.stock.management.StockManagementRequestType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ProductService.class})
public interface StockAuditMapper {

  @Mapping(target = "product", source = "productId")
  StockAudit toEntity(StockManagementRequestType request);
}
