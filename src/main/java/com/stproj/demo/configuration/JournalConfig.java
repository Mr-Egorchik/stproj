package com.stproj.demo.configuration;

import com.stproj.demo.entity.Journal;
import com.stproj.demo.service.JournalService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "journal")
@Setter
@RequiredArgsConstructor
public class JournalConfig {

    private String name;
    private int start;
    private String country;

    private final JournalService journalService;

    @Bean
    InitializingBean saveToDb() {
        return () -> journalService.save(new Journal(name, start, country));
    }
}
