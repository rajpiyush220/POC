package com.touch.blankspot.oauth.web.controller;

import com.touch.blankspot.oauth.service.FusionAuthService;
import com.touch.blankspot.oauth.web.service.type.AppRegistrationRequest;
import io.fusionauth.domain.User;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Tag(name = "User Management API")
@PreAuthorize("hasAuthority('admin')")
@RequestMapping("/api/v1/admin")
public class UserManagementController {

  @NonNull private final FusionAuthService fusionAuthService;

  @Operation(summary = "Get User by Email")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Success",
        content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        })
  })
  @GetMapping("/{email}")
  public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
    User user = fusionAuthService.getUserByEmail(email);
    return ResponseEntity.ok(user);
  }

  @Operation(summary = "Register User")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Success",
        content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        })
  })
  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody AppRegistrationRequest request) {
    User user = fusionAuthService.register(request);
    if (user != null) {
      return ResponseEntity.ok(user);
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
