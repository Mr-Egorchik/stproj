package com.stproj.demo.controller;

import com.stproj.demo.dto.StudentDto;
import com.stproj.demo.entity.Student;
import com.stproj.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @PostMapping("/")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto student) {
        logger.info("Start saving student...");
        studentService.save(mapper.map(student, Student.class));
        logger.info("Student is saved");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable("id") UUID id) {
        logger.info("Start deleting student...");
        studentService.delete(id);
        logger.info("Student is deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable("id") UUID id) {
        logger.info("Start finding student...");
        StudentDto student = studentService.findById(id);
        logger.info("Student is found");
        return ResponseEntity.ok(student);
    }

    @GetMapping("")
    public ResponseEntity<List<StudentDto>> findAllStudents(@ParameterObject Pageable pageable) {
        logger.info("Start finding students: page - " + pageable.getPageNumber() + ", size - " + pageable.getPageSize() + ", sort - " + pageable.getSort());
        List<StudentDto> students = studentService.findAll(pageable);
        logger.info("All students are found");
        return ResponseEntity.ok(students);
    }
}