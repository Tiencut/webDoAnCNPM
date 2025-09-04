package com.codecnpm.doancnpm.service;

import com.codecnpm.doancnpm.dto.CourseSectionDto;

import java.util.List;

public interface CourseSectionService {
    List<CourseSectionDto> getAllCourseSections();
    CourseSectionDto getCourseSectionById(Long id);
    CourseSectionDto createCourseSection(CourseSectionDto courseSectionDto);
    CourseSectionDto updateCourseSection(Long id, CourseSectionDto courseSectionDto);
    void deleteCourseSection(Long id);
}