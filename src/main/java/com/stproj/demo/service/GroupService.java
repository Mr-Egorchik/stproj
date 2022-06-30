package com.stproj.demo.service;

import com.stproj.demo.dto.GroupDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GroupService {

    private GroupRepository groupRepository;

    @Transactional
    public void save(Group group) {
        group.setId(UUID.randomUUID());
        groupRepository.save(group);
    }

    @Transactional
    public GroupDto findById(UUID uuid) {
        Group group = groupRepository.findById(uuid).orElse(null);
        return group == null ? null : new ModelMapper().map(group, GroupDto.class);
    }

    @Transactional
    public List<GroupDto> findAll() {
        return new ModelMapper().map(groupRepository.findAll(), new TypeToken<List<GroupDto>>(){}.getType());
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
