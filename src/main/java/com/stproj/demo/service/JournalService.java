package com.stproj.demo.service;

import com.stproj.demo.dto.JournalDto;
import com.stproj.demo.dto.StudentDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.entity.Journal;
import com.stproj.demo.entity.Student;
import com.stproj.demo.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class JournalService {

    private final JournalRepository journalRepository;
    private final ModelMapper mapper;

    @Transactional
    public void save(Journal journal) {
        journalRepository.save(journal);
    }

    @Transactional
    public JournalDto findById(UUID uuid) {
        Journal journal = journalRepository.findById(uuid).orElse(null);
        return journal == null ? null : mapper.map(journal, JournalDto.class);
    }

    @Transactional
    public List<JournalDto> findAll() {
        return mapper.map(journalRepository.findAll(), new TypeToken<List<JournalDto>>(){}.getType());
    }

    @Transactional
    public List<JournalDto> findAll(Pageable pageable) {
        return mapper.map(journalRepository.findAll(pageable).getContent(), new TypeToken<List<JournalDto>>(){}.getType());
    }

    @Transactional
    public void delete(UUID uuid) {
        journalRepository.deleteById(uuid);
    }

    @Transactional
    public void deleteAll() {
        journalRepository.deleteAll();
    }
}
