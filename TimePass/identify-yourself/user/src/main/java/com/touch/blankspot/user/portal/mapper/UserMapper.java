package com.touch.blankspot.user.portal.mapper;

import com.touch.blankspot.user.portal.apitype.ApiUserRequest;
import com.touch.blankspot.user.portal.apitype.ApiUserResponse;
import com.touch.blankspot.user.portal.data.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {
      // add if it is using any other mapper
    })
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
