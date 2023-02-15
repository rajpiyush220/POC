package com.touchblankspot.service.inventory.mapper;

import com.touchblankspot.service.inventory.data.model.Stock;
import com.touchblankspot.service.inventory.type.stock.management.StockManagementRequestType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockMapper {

  Stock toEntity(StockManagementRequestType request);
}
