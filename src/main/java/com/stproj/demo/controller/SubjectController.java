package com.stproj.demo.controller;

import com.stproj.demo.dto.SubjectDto;
import com.stproj.demo.entity.Subject;
import com.stproj.demo.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final ModelMapper mapper;

    @PostMapping("")
    public ResponseEntity<SubjectDto> saveSubject(@RequestBody SubjectDto subject) {
        log.info("Start saving subject...");
        subjectService.save(mapper.map(subject, Subject.class));
        log.info("Subject is saved");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<SubjectDto>> findAllSubjects(@ParameterObject Pageable pageable) {
        log.info("Start finding subjects: page - " + pageable.getPageNumber() + ", size - " + pageable.getPageSize() + ", sort - " + pageable.getSort());
        List<SubjectDto> subjects = subjectService.findAll(pageable);
        log.info("All subjects are found");
        return ResponseEntity.ok(subjects);
    }
}
