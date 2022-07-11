package com.stproj.demo.service;

import com.stproj.demo.dto.SubjectDto;
import com.stproj.demo.entity.Subject;
import com.stproj.demo.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static javax.management.timer.Timer.ONE_DAY;

@RequiredArgsConstructor
@Slf4j
@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapper mapper;

    @Transactional
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Transactional
    public void delete(UUID uuid) {
        subjectRepository.deleteById(uuid);
    }
    @Transactional
    public SubjectDto findById(UUID uuid) {
        return mapper.map(subjectRepository.findById(uuid), SubjectDto.class);
    }
    @Transactional
    @Cacheable("name")
    public List<SubjectDto> findAll(Pageable pageable) {
        return mapper.map(subjectRepository.findAll(pageable).getContent(), new TypeToken<List<SubjectDto>>(){}.getType());
    }

    @Scheduled(fixedRate = ONE_DAY)
    @CacheEvict(value = "name", allEntries = true)
    public void clearCache() {
        log.info("Cache \"name\" is cleared");
    }
}