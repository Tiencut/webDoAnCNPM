package com.codecnpm.doancnpm.controller;

import com.codecnpm.doancnpm.dto.CourseSectionDto;
import com.codecnpm.doancnpm.service.CourseSectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-sections")
public class CourseSectionController {

    private final CourseSectionService courseSectionService;

    public CourseSectionController(CourseSectionService courseSectionService) {
        this.courseSectionService = courseSectionService;
    }

    @GetMapping
    public ResponseEntity<List<CourseSectionDto>> getAllCourseSections() {
        List<CourseSectionDto> courseSections = courseSectionService.getAllCourseSections();
        return new ResponseEntity<>(courseSections, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseSectionDto> getCourseSectionById(@PathVariable("id") Long id) {
        CourseSectionDto courseSection = courseSectionService.getCourseSectionById(id);
        return new ResponseEntity<>(courseSection, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseSectionDto> createCourseSection(@RequestBody CourseSectionDto courseSectionDto) {
        CourseSectionDto createdCourseSection = courseSectionService.createCourseSection(courseSectionDto);
        return new ResponseEntity<>(createdCourseSection, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseSectionDto> updateCourseSection(@PathVariable("id") Long id, @RequestBody CourseSectionDto courseSectionDto) {
        CourseSectionDto updatedCourseSection = courseSectionService.updateCourseSection(id, courseSectionDto);
        return new ResponseEntity<>(updatedCourseSection, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseSection(@PathVariable("id") Long id) {
        courseSectionService.deleteCourseSection(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}