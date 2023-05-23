package com.touchblankspot.keycloak.config;

import com.touchblankspot.keycloak.auth.converter.JwtAuthConverter;
import com.touchblankspot.keycloak.auth.converter.CustomClaimConverter;
import com.touchblankspot.keycloak.auth.handler.KeycloakLogoutHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";

    private final String[] allowed_endpoints = {};

    @NonNull
    private final JwtAuthConverter jwtAuthConverter;

    @NonNull
    private final KeycloakLogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtDecoder jwtDecoder) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/sample/anonymous", "/sample/anonymous/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/sample/admin", "/sample/admin/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/sample/user").hasAnyRole(ADMIN, USER)
                .anyRequest().authenticated();
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter)
                .decoder(jwtDecoder);
        http.oauth2Login().and().logout().addLogoutHandler(logoutHandler).logoutSuccessUrl("/");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
      /*  NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(
                properties.getJwt().getJwkSetUri()).build();*/

        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/realms/java_integration/protocol/openid-connect/certs").build();


        jwtDecoder.setClaimSetConverter(CustomClaimConverter.builder().build());
        return jwtDecoder;
    }
}
