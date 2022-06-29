package com.stproj.demo.service;

import com.stproj.demo.entity.Student;
import com.stproj.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//
@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public Student findById(UUID uuid) {
        return studentRepository.findById(uuid).orElse(null);
    }

    @Transactional
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
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
