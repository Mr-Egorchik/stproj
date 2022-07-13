package com.stproj.demo.service;

import com.stproj.demo.dto.GroupDto;
import com.stproj.demo.dto.GroupResponseDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final ModelMapper mapper;

    @Transactional
    public GroupResponseDto save(Group group) {
        Group savedGroup = groupRepository.save(group);
        return new GroupResponseDto("", mapper.map(List.of(savedGroup), new TypeToken<List<GroupDto>>(){}.getType()), 1, 1, 0);
    }

    @Transactional
    public GroupResponseDto findById(UUID uuid) {
        Group group = groupRepository.findById(uuid).orElse(null);
        if (group == null) {
            return new GroupResponseDto("NO_DATA_FOUND", new ArrayList<>(), 0, 0, 0);
        }
        return new GroupResponseDto("", List.of(mapper.map(group, GroupDto.class)), 1, 1, 0);
    }

    @Transactional
    public List<GroupDto> findAll() {
        return mapper.map(groupRepository.findAll(), new TypeToken<List<GroupDto>>(){}.getType());
    }

    @Transactional
    public GroupResponseDto findAll(Pageable pageable) {
        Page<Group> page = groupRepository.findAll(pageable);
        return new GroupResponseDto(page.getContent().size() == 0 ? "NO_DATA_FOUND" : "", mapper.map(page.getContent(), new TypeToken<List<GroupDto>>(){}.getType()), page.getTotalPages(), page.getTotalElements(), pageable.getPageNumber());
    }

    @Transactional
    public GroupResponseDto getGroupsWithMoreThanTenStudentsNative(Pageable pageable) {
        Page<Group> page = groupRepository.getGroupsWithMoreThanTenStudentsNative(pageable);
        return new GroupResponseDto(page.getContent().size() == 0 ? "NO_DATA_FOUND" : "", mapper.map(page.getContent(), new TypeToken<List<GroupDto>>(){}.getType()), page.getTotalPages(), page.getTotalElements(), pageable.getPageNumber());
    }

    @Transactional
    public GroupResponseDto getGroupsWithMoreThanTenStudentsJPQL(Pageable pageable) {
        Page<Group> page = groupRepository.getGroupsWithMoreThanTenStudentsJPQL(pageable);
        return new GroupResponseDto(page.getContent().size() == 0 ? "NO_DATA_FOUND" : "", mapper.map(page.getContent(), new TypeToken<List<GroupDto>>(){}.getType()), page.getTotalPages(), page.getTotalElements(), pageable.getPageNumber());
    }

    @Transactional
    public List<GroupDto> getGroupsWithMoreThanTenStudentsCrud() {
        Iterable<Group> allGroups = groupRepository.findAll();
        List<Group> groups = new ArrayList<>();
        allGroups.forEach(group -> {
            if (group.getStudents().size() > 10) {
                groups.add(group);
            }
        });
        return mapper.map(groups, new TypeToken<List<GroupDto>>(){}.getType());
    }

    @Transactional
    public GroupResponseDto getGroupsWithMoreThanTenStudentsSpecification(Specification<Group> specification, Pageable pageable) {
        Page<Group> page = groupRepository.findAll(specification, pageable);
        return new GroupResponseDto(page.getContent().size() == 0 ? "NO_DATA_FOUND" : "", mapper.map(page.getContent(), new TypeToken<List<GroupDto>>(){}.getType()), page.getTotalPages(), page.getTotalElements(), pageable.getPageNumber());
    }
    @Transactional
    public void delete(UUID uuid) {
        groupRepository.deleteById(uuid);
    }

    @Transactional
    public void deleteAll() {
        groupRepository.deleteAll();
    }
}
