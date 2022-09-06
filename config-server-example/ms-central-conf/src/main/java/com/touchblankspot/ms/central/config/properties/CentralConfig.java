package com.touchblankspot.ms.central.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "centralconfig")
public class CentralConfig {
    DataSource dataSource;
    String env;

    @Data
    public static class DataSource{
        String url;
        String username;
        String password;
    }
}
