package com.codecnpm.doancnpm.mapper;

import com.codecnpm.doancnpm.dto.ProjectDto;
import com.codecnpm.doancnpm.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto toDto(Project project);
    Project toEntity(ProjectDto projectDto);
}