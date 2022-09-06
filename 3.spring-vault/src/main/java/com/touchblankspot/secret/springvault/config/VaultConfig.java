package com.touchblankspot.secret.springvault.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "db")
@Data
public class VaultConfig {
    private String username;
    private String password;
    private String driverClassName;
    private String url;
}
