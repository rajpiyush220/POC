package com.touch.blankspot.oauth.config;

import com.touch.blankspot.oauth.constant.FusionauthConstant;
import com.touch.blankspot.oauth.security.converter.OIDCJwtAuthConverter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SecurityConfig {

  @NonNull private final OAuth2ResourceServerProperties oauth2Properties;

  @NonNull private final OIDCJwtAuthConverter jwtAuthConverter;

  @Value("${oidc.issuer}")
  private String issuer;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, JwtDecoder jwtDecoder)
      throws Exception {
    http.cors()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers(FusionauthConstant.PERMIT_ALL)
        .permitAll()
        .anyRequest()
        .fullyAuthenticated()
        .and()
        .oauth2ResourceServer()
        .jwt()
        .decoder(jwtDecoder)
        .jwtAuthenticationConverter(jwtAuthConverter);
    return http.build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    final var jwtDecoder =
        NimbusJwtDecoder.withJwkSetUri(oauth2Properties.getJwt().getJwkSetUri()).build();
    final var withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
    jwtDecoder.setJwtValidator(withIssuer);
    return jwtDecoder;
  }
}
