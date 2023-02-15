package com.touch.blankspot.oauth.web.controller;

import com.touch.blankspot.oauth.service.FusionAuthService;
import com.touch.blankspot.oauth.web.service.type.AppRoleRequest;
import io.fusionauth.domain.ApplicationRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Tag(name = "Role Management API")
@PreAuthorize("hasAuthority('admin')")
@RequestMapping("/api/v1/admin")
public class RoleManagementController {

  @NonNull private final FusionAuthService fusionAuthService;

  @Operation(summary = "Create Role.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Success",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApplicationRole.class))
        })
  })
  @PostMapping("/user/role")
  public ResponseEntity<ApplicationRole> createRole(@RequestBody AppRoleRequest roleRequest) {
    ApplicationRole role = fusionAuthService.createRole(roleRequest);
    if (role != null) {
      return ResponseEntity.ok(role);
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
