package com.stproj.demo.repository;

import com.stproj.demo.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, UUID> {
}