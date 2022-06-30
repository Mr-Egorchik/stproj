package com.stproj.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Groups")
public class Group extends BaseEntity {

    @Column(name = "number", nullable = false)
    private String number;
    @OneToMany(mappedBy = "stGroup")
    private List<Student> students;
}