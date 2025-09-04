package com.codecnpm.doancnpm.service.impl;

import com.codecnpm.doancnpm.dto.LecturerDto;
import com.codecnpm.doancnpm.entity.Lecturer;
import com.codecnpm.doancnpm.mapper.LecturerMapper;
import com.codecnpm.doancnpm.repository.LecturerRepository;
import com.codecnpm.doancnpm.service.LecturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;
    private final LecturerMapper lecturerMapper;

    public LecturerServiceImpl(LecturerRepository lecturerRepository, LecturerMapper lecturerMapper) {
        this.lecturerRepository = lecturerRepository;
        this.lecturerMapper = lecturerMapper;
    }

    @Override
    public List<LecturerDto> getAllLecturers() {
        return lecturerRepository.findAll().stream()
                .map(lecturerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LecturerDto getLecturerById(Long id) {
        Lecturer lecturer = lecturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lecturer not found"));
        return lecturerMapper.toDto(lecturer);
    }

    @Override
    public LecturerDto createLecturer(LecturerDto lecturerDto) {
        Lecturer lecturer = lecturerMapper.toEntity(lecturerDto);
        return lecturerMapper.toDto(lecturerRepository.save(lecturer));
    }

    @Override
    public LecturerDto updateLecturer(Long id, LecturerDto lecturerDto) {
        Lecturer existingLecturer = lecturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lecturer not found"));
        existingLecturer.setMaxGroups(lecturerDto.getMaxGroups());
        // Update user if needed
        // existingLecturer.setUser(lecturerMapper.toEntity(lecturerDto.getUser()));
        return lecturerMapper.toDto(lecturerRepository.save(existingLecturer));
    }

    @Override
    public void deleteLecturer(Long id) {
        lecturerRepository.deleteById(id);
    }
}