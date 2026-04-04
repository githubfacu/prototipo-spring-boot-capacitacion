package com.example.tienda_demo.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.application")
public class AppConfig {

    private String name;
    private String description;
    private String version;
    private String language;

}
