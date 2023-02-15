package com.touchblankspot.service.inventory.mapper;

import com.touchblankspot.service.inventory.apitype.auth.RegisterUserResponse;
import com.touchblankspot.service.inventory.data.model.User;
import com.touchblankspot.service.inventory.type.auth.ApiRegisterUserRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
  User toEntity(ApiRegisterUserRequest request);

  @Mapping(target = "roles", source = "roleNames")
  RegisterUserResponse toApi(User user);

}
