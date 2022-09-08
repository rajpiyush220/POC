package com.touchblankspot.auth.jwt.springsecurityjwt.web;

import com.touchblankspot.auth.jwt.springsecurityjwt.annotation.UserPortalRestController;
import com.touchblankspot.auth.jwt.springsecurityjwt.security.service.CustomUserDetailsService;
import com.touchblankspot.auth.jwt.springsecurityjwt.security.type.AuthenticationRequest;
import com.touchblankspot.auth.jwt.springsecurityjwt.security.type.AuthenticationResponse;
import com.touchblankspot.auth.jwt.springsecurityjwt.security.util.JwtUtil;
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
  public ResponseEntity<AuthenticationResponse> generateToken(
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
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
