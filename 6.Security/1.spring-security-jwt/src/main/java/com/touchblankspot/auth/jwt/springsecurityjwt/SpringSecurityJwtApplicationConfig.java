package com.touchblankspot.auth.jwt.springsecurityjwt;

import com.touchblankspot.auth.jwt.springsecurityjwt.annotation.UserPortalRestController;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class SpringSecurityJwtApplicationConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(@NonNull PathMatchConfigurer configurer) {
                configurer.addPathPrefix(
                        "/security-jwt", HandlerTypePredicate.forAnnotation(UserPortalRestController.class));
            }
        };
    }
}
