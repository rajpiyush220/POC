package com.touchblankspot.auth.jwt.security;

import com.touchblankspot.auth.jwt.data.model.User;
import com.touchblankspot.auth.jwt.dto.CustomUserDetails;
import com.touchblankspot.auth.jwt.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @NonNull
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dbUser = userService.findByUserName(username);
        if (dbUser != null) {
            return constructUserInfo(dbUser);
        }
        log.error("Invalid user {}", username);
        throw new UsernameNotFoundException("Invalid user " + username);
    }

    private CustomUserDetails constructUserInfo(User dbUser) {
        return CustomUserDetails.builder()
                .username(dbUser.getUserName())
                .email(dbUser.getEmail())
                .password(dbUser.getPassword())
                .contactNo(dbUser.getContactNo())
                .authorities(dbUser.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toSet())).build();
    }
}
