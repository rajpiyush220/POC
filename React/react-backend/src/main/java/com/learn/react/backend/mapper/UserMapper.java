package com.learn.react.backend.mapper;

import com.learn.react.backend.data.domain.User;
import com.learn.react.backend.web.type.RegisterUserRequest;
import com.learn.react.backend.web.type.UserResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

  User toEntity(RegisterUserRequest request);

  UserResponse toApi(User user);

}
