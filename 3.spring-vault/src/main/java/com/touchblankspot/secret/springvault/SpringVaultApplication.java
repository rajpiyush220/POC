package com.touchblankspot.secret.springvault;

import com.touchblankspot.secret.springvault.config.VaultConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(VaultConfig.class)
public class SpringVaultApplication implements CommandLineRunner {

    @Autowired
    private VaultConfig configuration;

    public static void main(String[] args) {
        SpringApplication.run(SpringVaultApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Logger logger = LoggerFactory.getLogger(SpringVaultApplication.class);

        logger.info("----------------------------------------");
        logger.info("Configuration properties");
        logger.info("   example.username is {}", configuration.getUsername());
        logger.info("   example.password is {}", configuration.getPassword());
        logger.info("   example.url is {}", configuration.getUrl());
        logger.info("   example.driverClassName is {}", configuration.getDriverClassName());
        logger.info("----------------------------------------");
    }
}
