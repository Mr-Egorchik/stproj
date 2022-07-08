package com.stproj.demo.controller;

import com.stproj.demo.dto.StudentDto;
import com.stproj.demo.entity.Student;
import com.stproj.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper mapper;

    @PostMapping("/")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto student) {
        log.info("Start saving student...");
        studentService.save(mapper.map(student, Student.class));
        log.info("Student is saved");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable("id") UUID id) {
        log.info("Start deleting student...");
        studentService.delete(id);
        log.info("Student is deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable("id") UUID id) {
        log.info("Start finding student...");
        StudentDto student = studentService.findById(id);
        log.info("Student is found");
        return ResponseEntity.ok(student);
    }

    @GetMapping("")
    public ResponseEntity<List<StudentDto>> findAllStudents(@ParameterObject Pageable pageable) {
        log.info("Start finding students: page - " + pageable.getPageNumber() + ", size - " + pageable.getPageSize() + ", sort - " + pageable.getSort());
        List<StudentDto> students = studentService.findAll(pageable);
        log.info("All students are found");
        return ResponseEntity.ok(students);
    }
}