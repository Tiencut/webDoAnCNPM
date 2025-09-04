package com.codecnpm.doancnpm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String projectId;

    private String title;
    private String description;
    private String objectives;
    private String technologies;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    private LocalDateTime createdAt;

    public enum ProjectStatus {
        AVAILABLE,
        ASSIGNED,
        COMPLETED
    }
}