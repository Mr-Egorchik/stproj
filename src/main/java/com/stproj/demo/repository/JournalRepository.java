package com.stproj.demo.repository;

import com.stproj.demo.entity.Journal;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface JournalRepository extends PagingAndSortingRepository<Journal, UUID> {
}