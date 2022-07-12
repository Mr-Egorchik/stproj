package com.stproj.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "journal")
public class Journal extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "start")
    private int start;
    @Column(name = "country")
    private String country;
}
