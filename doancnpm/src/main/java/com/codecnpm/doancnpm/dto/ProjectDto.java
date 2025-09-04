package com.codecnpm.doancnpm.dto;

import java.time.LocalDateTime;

import com.codecnpm.doancnpm.entity.Project.ProjectStatus;

import lombok.Data;

@Data
public class ProjectDto {
    private Long projectId;
    private String title;
    private String description;
    private String objectives;
    private String technologies;
    private ProjectStatus status;
    // private GroupDto group;
    private LocalDateTime createdAt;
}