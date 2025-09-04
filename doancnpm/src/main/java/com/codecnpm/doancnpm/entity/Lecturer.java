package com.codecnpm.doancnpm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lecturers")
@Data
public class Lecturer {
    @Id
    private String lecturerId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer maxGroups;
}