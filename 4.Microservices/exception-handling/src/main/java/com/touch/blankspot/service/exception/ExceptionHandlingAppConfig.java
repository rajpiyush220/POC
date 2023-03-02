package com.touch.blankspot.service.exception;

import com.touch.blankspot.service.exception.annotations.ExceptionHandlerRestController;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ExceptionHandlingAppConfig {

  @Bean
  public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void configurePathMatch(@NonNull PathMatchConfigurer configurer) {
        configurer.addPathPrefix(
            "/exception-handling",
            HandlerTypePredicate.forAnnotation(ExceptionHandlerRestController.class));
      }
    };
  }
}
