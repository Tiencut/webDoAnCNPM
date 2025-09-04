package com.codecnpm.doancnpm.dto;

import lombok.Data;

@Data
public class CourseSectionDto {
    private Long courseSectionId;
    private String name;
    private String academicYear;
    private String semester;
    // private LecturerDto lecturer;
}