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

import java.util.ArrayList;
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
    public GroupResponseDto saveGroup(@RequestBody GroupDto group) {
        log.info("Start saving group...");
        GroupResponseDto response = groupService.save(mapper.map(group, Group.class));
        log.info("Group is saved");
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDto> deleteGroup(@PathVariable("id") UUID id) {
        log.info("Start deleting group...");
        groupService.delete(id);
        log.info("Group is deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public GroupResponseDto findGroupById(@PathVariable("id") UUID id) {
        log.info("Start finding group...");
        GroupResponseDto response = groupService.findById(id);
        log.info("Group is found");
        return response;
    }

    @GetMapping("")
    public GroupResponseDto findAllGroups(@ParameterObject Pageable pageable) {
        log.info("Start finding groups: page - " + pageable.getPageNumber() + ", size - " + pageable.getPageSize() + ", sort - " + pageable.getSort());
        GroupResponseDto response = groupService.findAll(pageable);
        log.info("All groups are found");
        return response;
    }

    @GetMapping("/more_than_ten_students_native")
    public GroupResponseDto getGroupsWithMoreThanTenStudentsNative() {
        log.info("NATIVE Start finding all groups with more than 10 students...");
        GroupResponseDto response = groupService.getGroupsWithMoreThanTenStudentsNative();
        log.info("All groups with more than 10 students are found");
        return response;
    }

    @GetMapping("/more_than_ten_students_jpql")
    public GroupResponseDto getGroupsWithMoreThanTenStudentsJPQL() {
        log.info("JPQL Start finding all groups with more than 10 students...");
        GroupResponseDto response = groupService.getGroupsWithMoreThanTenStudentsJPQL();
        log.info("All groups with more than 10 students are found");
        return response;
    }

    @GetMapping("/more_than_ten_students_crud")
    public GroupResponseDto getGroupsWithMoreThanTenStudentsCrud() {
        List<GroupDto> groups = groupService.getGroupsWithMoreThanTenStudentsCrud();
        log.info("All groups with more than 10 students are found");
        return new GroupResponseDto(HttpStatus.OK.toString(), groups, 1, groups.size(), 1);
    }

    @GetMapping("/more_than_ten_students_specification")
    public GroupResponseDto getGroupsWithMoreThanTenStudentsSpecification() {
        log.info("SPECIFICATION Start finding all groups with more than 10 students...");
        GroupResponseDto response = groupService.getGroupsWithMoreThanTenStudentsSpecification(new GroupSpecification());
        log.info("All groups with more than 10 students are found");
        return response;
    }



}
