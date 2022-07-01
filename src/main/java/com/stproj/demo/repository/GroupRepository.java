package com.stproj.demo.repository;

import com.stproj.demo.entity.Group;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface GroupRepository extends PagingAndSortingRepository<Group, UUID> {
}