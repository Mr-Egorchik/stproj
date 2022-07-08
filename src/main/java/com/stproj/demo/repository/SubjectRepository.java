package com.stproj.demo.repository;

import com.stproj.demo.entity.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface SubjectRepository extends PagingAndSortingRepository<Subject, UUID> {
}