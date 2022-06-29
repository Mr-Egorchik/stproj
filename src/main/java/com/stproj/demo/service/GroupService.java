package com.stproj.demo.service;

import com.stproj.demo.dto.GroupDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.repository.GroupRepository;
import com.stproj.demo.service.mapping.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupMapper groupMapper;

    @Transactional
    public void save(Group group) {
        group.setId(UUID.randomUUID());
        groupRepository.save(group);
    }

    @Transactional
    public GroupDto findById(UUID uuid) {
        Group group = groupRepository.findById(uuid).orElse(null);
        if (group == null) {
            return null;
        }
        return groupMapper.entityToDto(group);
    }

    @Transactional
    public List<GroupDto> findAll() {
        List<GroupDto> groups = new ArrayList<>();
        for (Group group: groupRepository.findAll()) {
            groups.add(groupMapper.entityToDto(group));
        }
        return groups;
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
