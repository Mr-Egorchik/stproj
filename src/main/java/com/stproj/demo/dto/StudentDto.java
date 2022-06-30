package com.stproj.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements Serializable {
    private UUID id;
    private String name;
    private int age;
    private String phone;
    private String email;
    private UUID stGroupId;
}
