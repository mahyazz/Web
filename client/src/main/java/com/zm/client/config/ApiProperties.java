package com.zm.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiProperties {

    private String key;

    public String getApiKey() {
        return key;
    }

    public void setApiKey(String key) {
        this.key = key;
    }
}
