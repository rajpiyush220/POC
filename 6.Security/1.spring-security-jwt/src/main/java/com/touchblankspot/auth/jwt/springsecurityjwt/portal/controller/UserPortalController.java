package com.touchblankspot.auth.jwt.springsecurityjwt.portal.controller;

import com.touchblankspot.auth.jwt.springsecurityjwt.annotation.UserPortalRestController;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.ApiUserRequest;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.ApiUserResponse;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.enums.RoleEnum;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.model.Role;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.model.RoleUsers;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.model.User;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.mapper.UserMapper;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.service.RoleService;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.service.RoleUsersService;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
  public ResponseEntity<List<ApiUserResponse>> getAll() {
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
    return ResponseEntity.ok().body(apiUserResponses);
  }

  @PostMapping("/users/register")
  public ResponseEntity<ApiUserResponse> registerUser(
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
    return ResponseEntity.ok().body(apiUserResponse);
  }
}
