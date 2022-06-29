package com.stproj.demo.service;

import com.stproj.demo.entity.Group;
import com.stproj.demo.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//
@Service
public class GroupService {

    @Autowired
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public Group findById(UUID uuid) {
        return groupRepository.findById(uuid).orElse(null);
    }

    @Transactional
    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        groupRepository.findAll().forEach(groups::add);
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
