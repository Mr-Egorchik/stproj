package com.stproj.demo.service;

import com.stproj.demo.dto.SubjectDto;
import com.stproj.demo.entity.Subject;
import com.stproj.demo.repository.SubjectRepository;
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
    public List<SubjectDto> findAll(Pageable pageable) {
        return mapper.map(subjectRepository.findAll(pageable).getContent(), new TypeToken<List<SubjectDto>>(){}.getType());
    }
}
