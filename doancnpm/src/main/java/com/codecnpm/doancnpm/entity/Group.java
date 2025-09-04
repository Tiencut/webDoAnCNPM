package com.codecnpm.doancnpm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "groups")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String groupId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne
    @JoinColumn(name = "course_section_id")
    private CourseSection courseSection;

    @OneToOne(mappedBy = "group")
    private Project project;

    @OneToMany(mappedBy = "group")
    private Set<Report> reports;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}