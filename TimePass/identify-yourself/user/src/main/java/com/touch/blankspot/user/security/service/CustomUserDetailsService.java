package com.touch.blankspot.user.security.service;

import com.touch.blankspot.user.portal.data.model.User;
import com.touch.blankspot.user.portal.service.UserService;
import com.touch.blankspot.user.security.CustomUserDetails;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  @NonNull private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User dbUser = userService.findByUserName(username);
    if (dbUser != null) {
      return new CustomUserDetails(dbUser);
    }
    log.error("Invalid user " + username);
    throw new UsernameNotFoundException("Invalid user " + username);
  }
}
