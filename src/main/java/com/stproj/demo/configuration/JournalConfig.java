package com.stproj.demo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "journal")
@Setter
@Getter
public class JournalConfig {

    private String name;
    private int start;
    private String country;
}
