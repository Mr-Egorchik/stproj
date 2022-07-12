package com.stproj.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponseDto {
    private String errorCode;
    private List<GroupDto> data;
    private int totalPages;
    private int totalElements;
    private int currentPage;
}
