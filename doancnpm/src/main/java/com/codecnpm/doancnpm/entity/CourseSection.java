package com.codecnpm.doancnpm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "course_sections")
@Data
public class CourseSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String courseSectionId;

    private String name;
    private String academicYear;
    private String semester;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}