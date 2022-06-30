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
    private final ModelMapper mapper;

    @Transactional
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public StudentDto findById(UUID uuid) {
        Student student = studentRepository.findById(uuid).orElse(null);
        return student == null ? null : mapper.map(student, StudentDto.class);
    }

    @Transactional
    public List<StudentDto> findAll() {
        return mapper.map(studentRepository.findAll(), new TypeToken<List<StudentDto>>(){}.getType());
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
