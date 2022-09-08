package com.touchblankspot.auth.jwt.springsecurityjwt.portal.controller;

import com.touchblankspot.auth.jwt.springsecurityjwt.annotation.UserPortalRestController;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.ApiRoleResponse;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.service.RoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.StreamSupport;

@UserPortalRestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserPortalRoleController {

  @NonNull private final RoleService roleService;

  @GetMapping("/roles")
  public ResponseEntity<List<ApiRoleResponse>> getAll() {
    List<ApiRoleResponse> apiRoleResponses =
        StreamSupport.stream(roleService.findAll().spliterator(), true)
            .map(role -> new ApiRoleResponse(role.getId(), role.getName()))
            .toList();
    return ResponseEntity.ok().body(apiRoleResponses);
  }
}
