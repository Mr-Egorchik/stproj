package com.stproj.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Groups")
public class Group extends BaseEntity {

    @Column(name = "number", nullable = false)
    private String number;
    @OneToMany(mappedBy = "st_group")
    private List<Student> students;
}