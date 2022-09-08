package com.touchblankspot.auth.jwt.springsecurityjwt.portal.mapper;

import com.touchblankspot.auth.jwt.springsecurityjwt.portal.ApiUserRequest;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.ApiUserResponse;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.model.User;
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
