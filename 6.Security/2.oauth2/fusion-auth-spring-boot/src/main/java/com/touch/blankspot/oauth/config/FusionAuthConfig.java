package com.touch.blankspot.oauth.config;

import io.fusionauth.client.FusionAuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FusionAuthConfig {

  @Value("${app.fusionauth.apiKey}")
  private String apiKey;

  @Value("${app.fusionauth.baseUrl}")
  private String baseUrl;

  @Bean
  public FusionAuthClient fusionAuthClient() {
    return new FusionAuthClient(apiKey, baseUrl);
  }
}
