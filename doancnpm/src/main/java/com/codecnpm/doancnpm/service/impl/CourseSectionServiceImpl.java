package com.codecnpm.doancnpm.service.impl;

import com.codecnpm.doancnpm.dto.CourseSectionDto;
import com.codecnpm.doancnpm.entity.CourseSection;
import com.codecnpm.doancnpm.mapper.CourseSectionMapper;
import com.codecnpm.doancnpm.repository.CourseSectionRepository;
import com.codecnpm.doancnpm.service.CourseSectionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseSectionServiceImpl implements CourseSectionService {

    private final CourseSectionRepository courseSectionRepository;
    private final CourseSectionMapper courseSectionMapper;

    public CourseSectionServiceImpl(CourseSectionRepository courseSectionRepository, CourseSectionMapper courseSectionMapper) {
        this.courseSectionRepository = courseSectionRepository;
        this.courseSectionMapper = courseSectionMapper;
    }

    @Override
    public List<CourseSectionDto> getAllCourseSections() {
        return courseSectionRepository.findAll().stream()
                .map(courseSectionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseSectionDto getCourseSectionById(Long id) {
        CourseSection courseSection = courseSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CourseSection not found"));
        return courseSectionMapper.toDto(courseSection);
    }

    @Override
    public CourseSectionDto createCourseSection(CourseSectionDto courseSectionDto) {
        CourseSection courseSection = courseSectionMapper.toEntity(courseSectionDto);
        return courseSectionMapper.toDto(courseSectionRepository.save(courseSection));
    }

    @Override
    public CourseSectionDto updateCourseSection(Long id, CourseSectionDto courseSectionDto) {
        CourseSection existingCourseSection = courseSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CourseSection not found"));
        existingCourseSection.setName(courseSectionDto.getName());
        existingCourseSection.setAcademicYear(courseSectionDto.getAcademicYear());
        existingCourseSection.setSemester(courseSectionDto.getSemester());
        // Update lecturer if needed
        // existingCourseSection.setLecturer(courseSectionMapper.toEntity(courseSectionDto.getLecturer()));
        return courseSectionMapper.toDto(courseSectionRepository.save(existingCourseSection));
    }

    @Override
    public void deleteCourseSection(Long id) {
        courseSectionRepository.deleteById(id);
    }
}