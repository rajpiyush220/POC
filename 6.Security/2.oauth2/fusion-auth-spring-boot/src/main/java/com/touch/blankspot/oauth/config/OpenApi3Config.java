package com.touch.blankspot.oauth.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OpenApi3Config {

  @NonNull private final BuildProperties buildProperties;

  @Value("${oidc.auth-url}")
  private String authUrl;

  @Value("${oidc.token-url}")
  private String tokenUrl;

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .components(
            new Components()
                .addSecuritySchemes(
                    "oauth2",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.OAUTH2)
                        .description("OAuth2 Flow")
                        .flows(
                            new OAuthFlows()
                                .authorizationCode(
                                    new OAuthFlow()
                                        .authorizationUrl(authUrl)
                                        .tokenUrl(tokenUrl)
                                        .scopes(new Scopes())))))
        .security(List.of(new SecurityRequirement().addList("oauth2")))
        .info(
            new Info()
                .title("FusionAuth Spring boot Integration API")
                .description(
                    """
                            Rest API to demonstrate Spring boot and Fusion auth integration.
                     """)
                .version(buildProperties.getVersion()));
  }
}
