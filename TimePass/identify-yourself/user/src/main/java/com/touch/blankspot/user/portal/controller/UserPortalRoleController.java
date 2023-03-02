package com.touch.blankspot.user.portal.controller;

import com.touch.blankspot.common.annotation.UserPortalRestController;
import com.touch.blankspot.common.error.ApiResponse;
import com.touch.blankspot.user.portal.apitype.ApiRoleResponse;
import com.touch.blankspot.user.portal.service.RoleService;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@UserPortalRestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserPortalRoleController {

  @NonNull private final RoleService roleService;

  @GetMapping("/roles")
  public ResponseEntity<ApiResponse<List<ApiRoleResponse>>> getAll() {
    List<ApiRoleResponse> apiRoleResponses =
        StreamSupport.stream(roleService.findAll().spliterator(), true)
            .map(role -> new ApiRoleResponse(role.getId(), role.getName()))
            .toList();
    return ResponseEntity.ok().body(new ApiResponse<>(apiRoleResponses));
  }
}
