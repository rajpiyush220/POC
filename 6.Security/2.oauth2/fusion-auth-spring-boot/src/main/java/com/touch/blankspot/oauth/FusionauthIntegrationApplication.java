package com.touch.blankspot.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FusionauthIntegrationApplication {

  public static void main(String[] args) {
    SpringApplication.run(FusionauthIntegrationApplication.class, args);
  }
}
