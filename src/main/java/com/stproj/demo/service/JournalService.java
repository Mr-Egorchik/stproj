package com.stproj.demo.service;

import com.stproj.demo.configuration.JournalConfig;
import com.stproj.demo.entity.Journal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RequiredArgsConstructor
public class JournalService {

    private final JournalDao journalDao;
    private final JournalConfig journalConfig;

    @PostConstruct
    public void postConstruct() {
        journalDao.save(new Journal(journalConfig.getName(), journalConfig.getStart(), journalConfig.getCountry()));
        log.info("Journal is saved");
    }
}
