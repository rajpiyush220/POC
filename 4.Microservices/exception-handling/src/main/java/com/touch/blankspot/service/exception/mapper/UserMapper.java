package com.touch.blankspot.service.exception.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {
      // add if it is using any other mapper
    })
public interface UserMapper {

  /*@Mapping(target = "userName", source = "userName")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "contactNo", source = "contactNo")
  User toEntity(ApiUserRequest request);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "userName", source = "userName")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "contactNo", source = "contactNo")
  @Mapping(target = "roles", source = "roles")
  ApiUserResponse toApi(User user);*/
}
