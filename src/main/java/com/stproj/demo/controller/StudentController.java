package com.stproj.demo.controller;

import com.stproj.demo.dto.StudentDto;
import com.stproj.demo.entity.Student;
import com.stproj.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    @PostMapping("/")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto student) {
        studentService.save(mapper.map(student, Student.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable("id") UUID id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable("id") UUID id) {
        StudentDto student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("")
    public ResponseEntity<List<StudentDto>> findAllStudents(@ParameterObject Pageable pageable) {
        List<StudentDto> students = studentService.findAll(pageable);
        return ResponseEntity.ok(students);
    }
}