package com.touchblankspot.auth.jwt.mapper;

import com.touchblankspot.auth.jwt.data.model.User;
import com.touchblankspot.auth.jwt.dto.request.ApiUserRequest;
import com.touchblankspot.auth.jwt.dto.response.ApiUserResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

  @Mapping(target = "userName", source = "userName")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "contactNo", source = "contactNo")
  User toEntity(ApiUserRequest request);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "userName", source = "userName")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "contactNo", source = "contactNo")
  @Mapping(target = "roles", source = "roles")
  ApiUserResponse toApi(User user);
}
