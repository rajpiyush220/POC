package com.touchblankspot.auth.jwt.config;

import com.touchblankspot.auth.jwt.annotations.JWTAuthRestController;
import com.touchblankspot.auth.jwt.constant.AppConstant;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(@NonNull PathMatchConfigurer configurer) {
                configurer.addPathPrefix(
                        AppConstant.JWT_AUTH_PATH, HandlerTypePredicate.forAnnotation(JWTAuthRestController.class));
            }
        };
    }
}
