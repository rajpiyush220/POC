package com.touch.blankspot.service.exception.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OpenApi3Config {

  @NonNull private final BuildProperties buildProperties;

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Microservices Exception Handling api")
                .description(
                    """
                            Rest API to demonstrate Spring boot exception handling.
                     """)
                .version(buildProperties.getVersion()));
  }
}
