package com.stproj.demo.dto;

import java.io.Serializable;
import java.util.UUID;

public record StudentDto(UUID id, String name, int age, String phone, String email,
                         UUID stGroupId) implements Serializable {
}
