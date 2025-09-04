package com.codecnpm.doancnpm.repository;

import com.codecnpm.doancnpm.entity.CourseSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSectionRepository extends JpaRepository<CourseSection, Long> {
}