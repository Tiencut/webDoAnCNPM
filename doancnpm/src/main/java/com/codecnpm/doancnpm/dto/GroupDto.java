package com.codecnpm.doancnpm.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GroupDto {
    private Long groupId;
    private String name;
    // private LecturerDto lecturer;
    // private CourseSectionDto courseSection;
    // private ProjectDto project;
    // private List<ReportDto> reports;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}