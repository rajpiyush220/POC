package com.touch.blankspot.oauth.security.converter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class OIDCJwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
  private static final String EMAIL_CLAIM = "email";
  private static final String ROLES_CLAIM = "roles";

  @Override
  public AbstractAuthenticationToken convert(final Jwt jwt) {
    return new UsernamePasswordAuthenticationToken(getUserName(jwt), "n/a", getAuthorities(jwt));
  }

  private String getUserName(final Jwt jwt) {
    return jwt.getClaimAsString(EMAIL_CLAIM);
  }

  private Collection<GrantedAuthority> getAuthorities(final Jwt jwt) {
    return this.getRoles(jwt).stream()
        .map(role -> new SimpleGrantedAuthority(role.toLowerCase()))
        .collect(Collectors.toSet());
  }

  private Collection<String> getRoles(final Jwt jwt) {
    final var claim = jwt.getClaims().get(ROLES_CLAIM);
    if (claim instanceof String roles && StringUtils.hasText(roles)) {
      return Arrays.asList(roles.split(" "));
    }
    if (claim instanceof Collection<?> roles) {
      return roles.stream().map(Object::toString).collect(Collectors.toSet());
    }
    return Collections.emptyList();
  }
}
