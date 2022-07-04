package com.stproj.demo.service;

import com.stproj.demo.dto.GroupDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final ModelMapper mapper;

    @Transactional
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public GroupDto findById(UUID uuid) {
        Group group = groupRepository.findById(uuid).orElse(null);
        return group == null ? null : mapper.map(group, GroupDto.class);
    }

    @Transactional
    public List<GroupDto> findAll() {
        return mapper.map(groupRepository.findAll(), new TypeToken<List<GroupDto>>(){}.getType());
    }

    @Transactional
    public List<GroupDto> findAll(Pageable pageable) {
        return mapper.map(groupRepository.findAll(pageable).getContent(), new TypeToken<List<GroupDto>>(){}.getType());
    }

    @Transactional
    public List<GroupDto> getGroupsWithMoreThanTenStudentsNative() {
        return mapper.map(groupRepository.getGroupsWithMoreThanTenStudentsNative(), new TypeToken<List<GroupDto>>(){}.getType());
    }

    @Transactional
    public List<GroupDto> getGroupsWithMoreThanTenStudentsJPQL() {
        return mapper.map(groupRepository.getGroupsWithMoreThanTenStudentsJPQL(), new TypeToken<List<GroupDto>>(){}.getType());
    }

    @Transactional
    public List<GroupDto> getGroupsWithMoreThanTenStudentsSpecification(Specification<Group> specification) {
        return mapper.map(groupRepository.findAll(specification), new TypeToken<List<GroupDto>>(){}.getType());
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
