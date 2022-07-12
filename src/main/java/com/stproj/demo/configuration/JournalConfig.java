package com.stproj.demo.configuration;

import com.stproj.demo.entity.Journal;
import com.stproj.demo.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JournalConfig {

    private final JournalService journalService;

    @Bean
    @ConfigurationProperties(prefix = "journal")
    public Journal journal() {
        return new Journal();
    }

    @Bean
    InitializingBean saveToDb() {
        return () -> journalService.save(journal());
    }
}
