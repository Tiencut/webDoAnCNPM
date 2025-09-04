package com.codecnpm.doancnpm.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportDto {
    private Long reportId;
    // private GroupDto group;
    private Integer weekNumber;
    private String content;
    private String feedback;
    private LocalDateTime createdAt;
}