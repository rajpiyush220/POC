package com.touch.blankspot.oauth.web.controller;

import com.touch.blankspot.oauth.service.FusionAuthService;
import com.touch.blankspot.oauth.web.service.type.AppLoginRequest;
import com.touch.blankspot.oauth.web.service.type.AppLoginResponse;
import com.touch.blankspot.oauth.web.service.type.AppUpdatePasswordRequest;
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
@Tag(name = "Login API")
@RequestMapping("/api/v1")
public class AuthController {

  @NonNull private final FusionAuthService fusionAuthService;

  @Operation(summary = "Generate auth token.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Success",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AppLoginResponse.class))
        })
  })
  @PostMapping("/login")
  @PreAuthorize("permitAll()")
  public ResponseEntity<AppLoginResponse> login(@RequestBody AppLoginRequest request) {
    AppLoginResponse loginResponse = fusionAuthService.performLogin(request);
    if (loginResponse != null) {
      return ResponseEntity.ok(loginResponse);
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Invalidate Tokens")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Success",
        content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
        })
  })
  @PostMapping("/logout")
  @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
  public ResponseEntity<String> logout() {
    return ResponseEntity.ok(fusionAuthService.performLogout());
  }

  @Operation(summary = "Update User Password")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Success",
        content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
        })
  })
  @PostMapping("/changePassword")
  @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
  public ResponseEntity<String> updatePassword(@RequestBody AppUpdatePasswordRequest request) {
    Boolean status = fusionAuthService.updatePassword(request);
    if (status) {
      return ResponseEntity.ok("Password updated successfully.");
    } else {
      return new ResponseEntity<>("Unable to updated password.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
