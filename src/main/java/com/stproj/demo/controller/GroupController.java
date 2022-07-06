package com.stproj.demo.controller;

import com.stproj.demo.dto.GroupDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.service.GroupService;
import com.stproj.demo.specification.GroupSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public ResponseEntity<GroupDto> saveGroup(@RequestBody GroupDto group) {
        log.info("Start saving group...");
        groupService.save(mapper.map(group, Group.class));
        log.info("Group is saved");
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDto> deleteGroup(@PathVariable("id") UUID id) {
        log.info("Start deleting group...");
        groupService.delete(id);
        log.info("Group is deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> findGroupById(@PathVariable("id") UUID id) {
        log.info("Start finding group...");
        GroupDto group = groupService.findById(id);
        log.info("Group is found");
        return ResponseEntity.ok(group);
    }
    @GetMapping("")
    public ResponseEntity<List<GroupDto>> findAllGroups(@ParameterObject Pageable pageable) {
        log.info("Start finding groups: page - " + pageable.getPageNumber() + ", size - " + pageable.getPageSize() + ", sort - " + pageable.getSort());
        List<GroupDto> groups = groupService.findAll(pageable);
        log.info("All groups are found");
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/more_than_ten_students_native")
    public ResponseEntity<List<GroupDto>> getGroupsWithMoreThanTenStudentsNative() {
        log.info("NATIVE Start finding all groups with more than 10 students...");
        List<GroupDto> groups = groupService.getGroupsWithMoreThanTenStudentsNative();
        log.info("All groups with more than 10 students are found");
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/more_than_ten_students_jpql")
    public ResponseEntity<List<GroupDto>> getGroupsWithMoreThanTenStudentsJPQL() {
        log.info("JPQL Start finding all groups with more than 10 students...");
        List<GroupDto> groups = groupService.getGroupsWithMoreThanTenStudentsJPQL();
        log.info("All groups with more than 10 students are found");
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/more_than_ten_students_crud")
    public ResponseEntity<List<GroupDto>> getGroupsWithMoreThanTenStudentsCrud() {
        List<GroupDto> groups = groupService.getGroupsWithMoreThanTenStudentsCrud();
        log.info("All groups with more than 10 students are found");
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/more_than_ten_students_specification")
    public ResponseEntity<List<GroupDto>> getGroupsWithMoreThanTenStudentsSpecification() {
        log.info("SPECIFICATION Start finding all groups with more than 10 students...");
        List<GroupDto> groups = groupService.getGroupsWithMoreThanTenStudentsSpecification(new GroupSpecification());
        log.info("All groups with more than 10 students are found");
        return ResponseEntity.ok(groups);
    }



}
