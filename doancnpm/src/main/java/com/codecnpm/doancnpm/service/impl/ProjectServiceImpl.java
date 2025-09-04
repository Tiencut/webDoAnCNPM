package com.codecnpm.doancnpm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codecnpm.doancnpm.dto.ProjectDto;
import com.codecnpm.doancnpm.entity.Project;
import com.codecnpm.doancnpm.mapper.ProjectMapper;
import com.codecnpm.doancnpm.repository.ProjectRepository;
import com.codecnpm.doancnpm.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectMapper.toDto(project);
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        return projectMapper.toDto(projectRepository.save(project));
    }

    @Override
    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        existingProject.setTitle(projectDto.getTitle());
        existingProject.setDescription(projectDto.getDescription());
        existingProject.setObjectives(projectDto.getObjectives());
        existingProject.setTechnologies(projectDto.getTechnologies());
        existingProject.setStatus(projectDto.getStatus());
        // Update group if needed
        return projectMapper.toDto(projectRepository.save(existingProject));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}