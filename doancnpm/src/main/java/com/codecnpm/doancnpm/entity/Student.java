package com.codecnpm.doancnpm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    private String studentId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String major;
    private String academicYear;

    @ManyToOne
    @JoinColumn(name = "course_section_id")
    private CourseSection courseSection;
}