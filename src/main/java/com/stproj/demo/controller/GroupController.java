package com.stproj.demo.controller;

import com.stproj.demo.dto.GroupDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private ModelMapper mapper;

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
    @GetMapping("/")
    public ResponseEntity<List<GroupDto>> findAllGroups() {
        List<GroupDto> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }
}
