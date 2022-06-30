package com.stproj.demo.service;

import com.stproj.demo.dto.GroupDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepository groupRepository;
    @Autowired
    private ModelMapper mapper;

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
    public void delete(UUID uuid) {
        groupRepository.deleteById(uuid);
    }

    @Transactional
    public void deleteAll() {
        groupRepository.deleteAll();
    }
}
