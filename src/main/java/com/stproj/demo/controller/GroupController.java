package com.stproj.demo.controller;

import com.stproj.demo.dto.GroupDto;
import com.stproj.demo.dto.GroupResponseDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.service.GroupService;
import com.stproj.demo.specification.GroupSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;
    private final ModelMapper mapper;

    @PostMapping("/")
    public ResponseEntity<GroupResponseDto> saveGroup(@RequestBody GroupDto group) {
        log.info("Start saving group...");
        GroupResponseDto response = groupService.save(mapper.map(group, Group.class));
        log.info("Group is saved");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDto> deleteGroup(@PathVariable("id") UUID id) {
        log.info("Start deleting group...");
        groupService.delete(id);
        log.info("Group is deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDto> findGroupById(@PathVariable("id") UUID id) {
        log.info("Start finding group...");
        GroupResponseDto response = groupService.findById(id);
        log.info("Group is found");
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<GroupResponseDto> findAllGroups(@ParameterObject Pageable pageable) {
        log.info("Start finding groups: page - " + pageable.getPageNumber() + ", size - " + pageable.getPageSize() + ", sort - " + pageable.getSort());
        GroupResponseDto response = groupService.findAll(pageable);
        log.info("All groups are found");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/more_than_ten_students_native")
    public ResponseEntity<GroupResponseDto> getGroupsWithMoreThanTenStudentsNative(@ParameterObject Pageable pageable) {
        log.info("NATIVE Start finding all groups with more than 10 students...");
        GroupResponseDto response = groupService.getGroupsWithMoreThanTenStudentsNative(pageable);
        log.info("All groups with more than 10 students are found");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/more_than_ten_students_jpql")
    public ResponseEntity<GroupResponseDto> getGroupsWithMoreThanTenStudentsJPQL(@ParameterObject Pageable pageable) {
        log.info("JPQL Start finding all groups with more than 10 students...");
        GroupResponseDto response = groupService.getGroupsWithMoreThanTenStudentsJPQL(pageable);
        log.info("All groups with more than 10 students are found");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/more_than_ten_students_specification")
    public ResponseEntity<GroupResponseDto> getGroupsWithMoreThanTenStudentsSpecification(@ParameterObject Pageable pageable) {
        log.info("SPECIFICATION Start finding all groups with more than 10 students...");
        GroupResponseDto response = groupService.getGroupsWithMoreThanTenStudentsSpecification(new GroupSpecification(), pageable);
        log.info("All groups with more than 10 students are found");
        return ResponseEntity.ok(response);
    }

}
