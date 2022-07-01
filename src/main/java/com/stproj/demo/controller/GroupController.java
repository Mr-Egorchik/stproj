package com.stproj.demo.controller;

import com.stproj.demo.dto.GroupDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;
    private final ModelMapper mapper;

    @PostMapping("/")
    public ResponseEntity<GroupDto> saveGroup(@RequestBody GroupDto group) {
        groupService.save(mapper.map(group, Group.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDto> deleteGroup(@PathVariable("id") UUID id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> findGroupById(@PathVariable("id") UUID id) {
        GroupDto group = groupService.findById(id);
        return ResponseEntity.ok(group);
    }
    @GetMapping("")
    public ResponseEntity<List<GroupDto>> findAllGroups(Pageable pageable) {
        List<GroupDto> groups = groupService.findAll(pageable);
        return ResponseEntity.ok(groups);
    }
}
