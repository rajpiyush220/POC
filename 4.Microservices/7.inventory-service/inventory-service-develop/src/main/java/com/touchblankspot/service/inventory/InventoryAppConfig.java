package com.touchblankspot.service.inventory;

import com.touchblankspot.service.inventory.service.RoleCacheService;
import com.touchblankspot.service.inventory.service.security.CustomUserDetailsService;
import com.touchblankspot.service.inventory.web.annotations.AuthRestController;
import com.touchblankspot.service.inventory.web.annotations.InventoryRestController;
import com.touchblankspot.service.inventory.web.annotations.ProductRestController;
import com.touchblankspot.service.inventory.web.annotations.SalesRestController;
import com.touchblankspot.service.inventory.web.annotations.StockRestController;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class InventoryAppConfig {

  @NonNull
  private final CustomUserDetailsService userDetailsService;

  @NonNull
  private final RoleCacheService roleCacheService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeHttpRequests((requests) -> requests.requestMatchers("/**").permitAll());
    http.headers().frameOptions().sameOrigin();
    return http.build();
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    return daoAuthenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration)
      throws Exception {
    return configuration.getAuthenticationManager();
  }

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationReadyEvent() {
    Executors.newSingleThreadScheduledExecutor()
        .schedule(this::runAfterStartup, 20, TimeUnit.SECONDS);
  }

  @Bean
  public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(
            "/inventory", HandlerTypePredicate.forAnnotation(InventoryRestController.class));
        configurer.addPathPrefix(
            "/product", HandlerTypePredicate.forAnnotation(ProductRestController.class));
        configurer.addPathPrefix(
            "/sales", HandlerTypePredicate.forAnnotation(SalesRestController.class));
        configurer.addPathPrefix(
            "/stock", HandlerTypePredicate.forAnnotation(StockRestController.class));
        configurer.addPathPrefix("/auth",
            HandlerTypePredicate.forAnnotation(AuthRestController.class));
      }
    };
  }

  public void runAfterStartup() {
    roleCacheService.clearAllRolePermissionCache();
    log.info("Application role cache flushed");
  }
}
