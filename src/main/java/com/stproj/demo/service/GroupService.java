package com.stproj.demo.service;

import com.stproj.demo.entity.Group;
import com.stproj.demo.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GroupService {

    @Autowired
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void save(Group group) {
        groupRepository.save(group);
    }

    public Group findById(UUID uuid) {
        return groupRepository.findById(uuid).orElse(null);
    }

    public List<Group> findAll() {
        return (List<Group>) groupRepository.findAll();
    }

    public void delete(UUID uuid) {
        if (!groupRepository.existsById(uuid))
            throw new NoSuchElementException();
        groupRepository.deleteById(uuid);
    }

    public void deleteAll() {
        groupRepository.deleteAll();
    }
}
