package com.stproj.demo.service;

import com.stproj.demo.dto.StudentDto;
import com.stproj.demo.entity.Student;
import com.stproj.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public void save(Student student) {
        student.setId(UUID.randomUUID());
        studentRepository.save(student);
    }

    @Transactional
    public StudentDto findById(UUID uuid) {
        Student student = studentRepository.findById(uuid).orElse(null);
        return student == null ? null : new ModelMapper().map(student, StudentDto.class);
    }

    @Transactional
    public List<StudentDto> findAll() {
        return new ModelMapper().map(studentRepository.findAll(), new TypeToken<List<StudentDto>>(){}.getType());
    }

    @Transactional
    public void delete(UUID uuid) {
        studentRepository.deleteById(uuid);
    }

    @Transactional
    public void deleteAll() {
        studentRepository.deleteAll();
    }
}
