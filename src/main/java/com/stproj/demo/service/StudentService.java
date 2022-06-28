package com.stproj.demo.service;

import com.stproj.demo.entity.Student;
import com.stproj.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student findById(UUID uuid) {
        return studentRepository.findById(uuid).orElse(null);
    }

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    public void delete(UUID uuid) {
        if (!studentRepository.existsById(uuid))
            throw new NoSuchElementException();
        studentRepository.deleteById(uuid);
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }
}
