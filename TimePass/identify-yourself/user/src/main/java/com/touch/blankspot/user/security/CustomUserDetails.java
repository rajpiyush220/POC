package com.touch.blankspot.user.security;

import com.touch.blankspot.user.portal.data.model.User;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class CustomUserDetails implements UserDetails {

  private String username;

  private String email;

  private String password;

  private String contactNo;

  private Set<GrantedAuthority> authorities;

  public CustomUserDetails(User user) {
    this.username = user.getUserName();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.contactNo = user.getContactNo();
    this.authorities =
        user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toSet());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
