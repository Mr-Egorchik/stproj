package com.stproj.demo.service.mapping;

import com.stproj.demo.dto.StudentDto;
import com.stproj.demo.entity.Group;
import com.stproj.demo.entity.Student;
import com.stproj.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public StudentDto entityToDto(Student student) {
        return new StudentDto(student.getId(),
                student.getName(),
                student.getAge(),
                student.getPhone(),
                student.getEmail(),
                student.getStGroup().getId());
    }
    public Student dtoToEntity(StudentDto studentDto, Group group) {
        Student student = new Student(studentDto.name(), studentDto.age(), studentDto.phone(), studentDto.email(), group);
        student.setId(studentDto.id());
        return student;
    }
}
