package com.touchblankspot.service.inventory.mapper;

import com.touchblankspot.service.inventory.data.model.SalesDetails;
import com.touchblankspot.service.inventory.type.sales.SalesDetailRequestType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalesDetailMapper {

  SalesDetails toEntity(SalesDetailRequestType request);
}
