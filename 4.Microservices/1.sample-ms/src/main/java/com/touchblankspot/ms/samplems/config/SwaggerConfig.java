package com.touchblankspot.ms.samplems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket api() {
        var apiInfo = new ApiInfoBuilder()
                .title("Sample Microservices With Swagger")
                .version("1.0")
                .license(null)
                .licenseUrl(null)
                .termsOfServiceUrl(null)
                .description("The Sample Microservices API to show the integration of swagger ui.")
                .build();

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .apis(RequestHandlerSelectors.basePackage("com.touchblankspot.ms.samplems"))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
}

