package com.stproj.demo.service;

import com.stproj.demo.dto.GroupDto;
import com.stproj.demo.dto.GroupResponseDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
        return new GroupResponseDto(HttpStatus.OK.toString(), mapper.map(List.of(savedGroup), new TypeToken<List<GroupDto>>(){}.getType()), 1, 1, 0);
    }

    @Transactional
    public GroupResponseDto findById(UUID uuid) {
        Group group = groupRepository.findById(uuid).orElse(null);
        if (group == null) {
            return new GroupResponseDto(HttpStatus.OK.toString(), new ArrayList<>(), 1, 0, 0);
        }
        return new GroupResponseDto(HttpStatus.OK.toString(), List.of(mapper.map(group, GroupDto.class)), 1, 1, 0);
    }

    @Transactional
    public List<GroupDto> findAll() {
        return mapper.map(groupRepository.findAll(), new TypeToken<List<GroupDto>>(){}.getType());
    }

    @Transactional
    public GroupResponseDto findAll(Pageable pageable) {
        Page<Group> page = groupRepository.findAll(pageable);
        return new GroupResponseDto(HttpStatus.OK.toString(), mapper.map(page.getContent(), new TypeToken<List<GroupDto>>(){}.getType()), page.getTotalPages(), page.getTotalElements(), pageable.getPageNumber());
    }

    @Transactional
    public GroupResponseDto getGroupsWithMoreThanTenStudentsNative() {
        List<GroupDto> groups = mapper.map(groupRepository.getGroupsWithMoreThanTenStudentsNative(), new TypeToken<List<GroupDto>>(){}.getType());
        return new GroupResponseDto(HttpStatus.OK.toString(), groups, 1, groups.size(), 0);
    }

    @Transactional
    public GroupResponseDto getGroupsWithMoreThanTenStudentsJPQL() {
        List<GroupDto> groups = mapper.map(groupRepository.getGroupsWithMoreThanTenStudentsJPQL(), new TypeToken<List<GroupDto>>(){}.getType());
        return new GroupResponseDto(HttpStatus.OK.toString(), groups, 1, groups.size(), 0);
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
    public GroupResponseDto getGroupsWithMoreThanTenStudentsSpecification(Specification<Group> specification) {
        List<GroupDto> groups = mapper.map(groupRepository.findAll(specification), new TypeToken<List<GroupDto>>(){}.getType());
        return new GroupResponseDto(HttpStatus.OK.toString(), groups, 1, groups.size(), 0);
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
