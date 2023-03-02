package com.touch.blankspot.user.portal.controller;

import com.touch.blankspot.common.annotation.UserPortalRestController;
import com.touch.blankspot.common.error.ApiResponse;
import com.touch.blankspot.user.portal.apitype.ApiUserRequest;
import com.touch.blankspot.user.portal.apitype.ApiUserResponse;
import com.touch.blankspot.user.portal.data.enums.RoleEnum;
import com.touch.blankspot.user.portal.data.model.User;
import com.touch.blankspot.user.portal.mapper.UserMapper;
import com.touch.blankspot.user.portal.service.RoleService;
import com.touch.blankspot.user.portal.service.RoleUsersService;
import com.touch.blankspot.user.portal.service.UserService;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import com.touch.blankspot.user.portal.data.model.Role;
import com.touch.blankspot.user.portal.data.model.RoleUsers;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@UserPortalRestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserPortalController {

  @NonNull private final UserService userService;

  @NonNull private final RoleService roleService;

  @NonNull private final RoleUsersService roleUsersService;

  @NonNull private final PasswordEncoder encoder;

  @NonNull private final UserMapper userMapper;

  @GetMapping("/users")
  public ResponseEntity<ApiResponse<List<ApiUserResponse>>> getAll() {
    List<ApiUserResponse> apiUserResponses =
        userService.findAll().stream()
            .map(
                user -> {
                  ApiUserResponse apiUserResponse = userMapper.toApi(user);
                  apiUserResponse.setUserRoles(
                      user.getRoles().stream().map(Role::getName).toList());
                  return apiUserResponse;
                })
            .toList();
    return ResponseEntity.ok().body(new ApiResponse<>(apiUserResponses));
  }

  @PostMapping("/users/register")
  public ResponseEntity<ApiResponse<ApiUserResponse>> registerUser(
      @Valid @RequestBody ApiUserRequest request) {
    User user = userMapper.toEntity(request);
    Set<Role> roles =
        roleService.findByName(request.getRoles().stream().map(RoleEnum::name).toList());
    user.setRoles(roles);
    user.setPassword(encoder.encode(request.getPassword()));
    user = userService.save(user);
    UUID userId = user.getId();
    roleUsersService.save(roles.stream().map(role -> new RoleUsers(userId, role.getId())).toList());
    ApiUserResponse apiUserResponse = userMapper.toApi(user);
    apiUserResponse.setUserRoles(apiUserResponse.getRoles().stream().map(Role::getName).toList());
    return ResponseEntity.ok().body(new ApiResponse<>(apiUserResponse));
  }
}
