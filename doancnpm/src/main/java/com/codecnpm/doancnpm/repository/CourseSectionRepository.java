package com.codecnpm.doancnpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codecnpm.doancnpm.entity.CourseSection;

@Repository
public interface CourseSectionRepository extends JpaRepository<CourseSection, Long> {
}