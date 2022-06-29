package com.stproj.demo.service;

import com.stproj.demo.dto.StudentDto;
import com.stproj.demo.entity.Student;
import com.stproj.demo.repository.StudentRepository;
import com.stproj.demo.service.mapping.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    public void save(Student student) {
        student.setId(UUID.randomUUID());
        studentRepository.save(student);
    }

    @Transactional
    public StudentDto findById(UUID uuid) {
        Student student = studentRepository.findById(uuid).orElse(null);
        if (student == null) {
            return null;
        }
        return studentMapper.entityToDto(student);
    }

    @Transactional
    public List<StudentDto> findAll() {
        List<StudentDto> students = new ArrayList<>();
        for (Student student: studentRepository.findAll()) {
            students.add(studentMapper.entityToDto(student));
        }
        return students;
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
