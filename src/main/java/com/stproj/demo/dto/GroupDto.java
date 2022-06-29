package com.stproj.demo.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record GroupDto(UUID id, String number, List<UUID> studentIds) implements Serializable {
}
