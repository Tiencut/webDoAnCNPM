package com.codecnpm.doancnpm.mapper;

import com.codecnpm.doancnpm.dto.CourseSectionDto;
import com.codecnpm.doancnpm.entity.CourseSection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseSectionMapper {
    CourseSectionMapper INSTANCE = Mappers.getMapper(CourseSectionMapper.class);

    CourseSectionDto toDto(CourseSection courseSection);
    CourseSection toEntity(CourseSectionDto courseSectionDto);
}