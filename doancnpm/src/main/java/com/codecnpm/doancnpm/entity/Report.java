package com.codecnpm.doancnpm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String reportId;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    private Integer weekNumber;
    private String content;
    private String feedback;
    private LocalDateTime createdAt;
}