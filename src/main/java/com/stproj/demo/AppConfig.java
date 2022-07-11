package com.stproj.demo;

import com.stproj.demo.entity.Journal;
import com.stproj.demo.repository.JournalRepository;
import com.stproj.demo.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableCaching
@EnableScheduling
@EnableConfigurationProperties
@RequiredArgsConstructor
public class AppConfig {

    private final JournalRepository journalRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @ConfigurationProperties(prefix = "journal")
    public Journal journal() {
        return new Journal();
    }
    @Bean
    InitializingBean saveToDb() {
        return () -> journalRepository.save(journal());
    }
}
