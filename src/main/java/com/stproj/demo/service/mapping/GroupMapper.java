package com.stproj.demo.service.mapping;

import com.stproj.demo.dto.GroupDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.entity.Student;
import com.stproj.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupMapper {

    public GroupDto entityToDto(Group group) {
        List<UUID> students = new ArrayList<>();
        for (Student student: group.getStudents()) {
            students.add(student.getId());
        }
        return new GroupDto(group.getId(),
                group.getNumber(),
                students);
    }

    public Group dtoToEntity(GroupDto groupDto, List<Student> students) {
        Group group = new Group(groupDto.number(), students);
        group.setId(groupDto.id());
        return group;
    }

}
