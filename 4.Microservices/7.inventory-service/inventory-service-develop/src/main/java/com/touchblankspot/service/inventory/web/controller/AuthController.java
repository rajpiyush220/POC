package com.touchblankspot.service.inventory.web.controller;

import static com.touchblankspot.service.inventory.data.enums.RoleEnum.SUPER_ADMIN;

import com.touchblankspot.common.error.ApiError;
import com.touchblankspot.common.error.ApiResponse;
import com.touchblankspot.service.inventory.apitype.auth.RegisterUserResponse;
import com.touchblankspot.service.inventory.data.model.User;
import com.touchblankspot.service.inventory.mapper.UserMapper;
import com.touchblankspot.service.inventory.service.UserService;
import com.touchblankspot.service.inventory.service.security.SecurityService;
import com.touchblankspot.service.inventory.type.auth.ApiRegisterUserRequest;
import com.touchblankspot.service.inventory.web.annotations.AuthRestController;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AuthRestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthController {

  @NonNull
  private final UserService userService;
  @NonNull
  private final SecurityService securityService;
  @NonNull
  private final UserMapper userMapper;

  @PostMapping("/register/super/admin")
  public ApiResponse<Object> registration(
      @Valid @RequestBody ApiRegisterUserRequest request) {
    try {
      if (userService.isSuperAdminExists()) {
        return ApiResponse.builder().success(false)
            .message("Super admin already exists. You can not have multiple super admin").build();
      }
      User user = userService.save(userMapper.toEntity(request), SUPER_ADMIN.name());
      return ApiResponse.builder().success(true).message("User registered successfully")
          .payload(userMapper.toApi(user)).build();
    } catch (Exception ex) {
      log.error("Unable to create super admin ", ex);
      return ApiResponse.builder().success(false).message(ex.getMessage()).build();
    }
  }
}
