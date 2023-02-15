package com.touch.blankspot.oauth.web.controller;

import com.touch.blankspot.oauth.web.service.type.TestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Integration Test API")
public class TestController {

  @Operation(summary = "Get some data for anyone")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Success",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = TestResponse.class))
        })
  })
  @GetMapping("/anyone")
  @PreAuthorize("permitAll()")
  public ResponseEntity<TestResponse> allowAnyone(Authentication authentication) {
    return ResponseEntity.ok(
        new TestResponse(
            "Anyone", isAuthenticated(authentication), getAuthorities(authentication)));
  }

  @Operation(summary = "Get some data for user users")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Success",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = TestResponse.class))
        })
  })
  @GetMapping("/user")
  @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
  public ResponseEntity<TestResponse> allowBasicUser(Authentication authentication) {
    return ResponseEntity.ok(
        new TestResponse(
            "Basic User", isAuthenticated(authentication), getAuthorities(authentication)));
  }

  @Operation(summary = "Get some data for admin users")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Success",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = TestResponse.class))
        })
  })
  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<TestResponse> allowAdminUser(Authentication authentication) {
    return ResponseEntity.ok(
        new TestResponse(
            "Admin User", isAuthenticated(authentication), getAuthorities(authentication)));
  }

  private boolean isAuthenticated(Authentication authentication) {
    return authentication != null && authentication.isAuthenticated();
  }

  private String getAuthorities(Authentication authentication) {
    return isAuthenticated(authentication) ? authentication.getAuthorities().toString() : "";
  }
}
