package com.stproj.demo.repository;

import com.stproj.demo.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface StudentRepository extends PagingAndSortingRepository<Student, UUID> {
}