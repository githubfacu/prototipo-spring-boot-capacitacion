package com.example.tienda_demo.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "store")
@PropertySource(value = "classpath:parameters.properties")
public class StoreParametersConfig {

    private String currency;
    private String region;

}
