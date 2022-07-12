package com.stproj.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponseDto {
    private int errorCode;
    private List<StudentDto> data;
    private int totalPages;
    private int totalElements;
    private int currentPage;
}
