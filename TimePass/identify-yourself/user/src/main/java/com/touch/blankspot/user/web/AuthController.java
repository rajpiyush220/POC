package com.touch.blankspot.user.web;

import com.touch.blankspot.common.annotation.UserPortalRestController;
import com.touch.blankspot.common.error.ApiResponse;
import com.touch.blankspot.user.security.api.contract.type.AuthenticationRequest;
import com.touch.blankspot.user.security.api.contract.type.AuthenticationResponse;
import com.touch.blankspot.user.security.service.CustomUserDetailsService;
import com.touch.blankspot.user.security.util.JwtUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@UserPortalRestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

  @NonNull private AuthenticationManager authenticationManager;

  @NonNull private JwtUtil jwtTokenUtil;

  @NonNull private CustomUserDetailsService userDetailsService;

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<AuthenticationResponse>> generateToken(
      @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    }
    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String jwt = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok(new ApiResponse<>(new AuthenticationResponse(jwt)));
  }
}
