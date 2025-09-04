package com.codecnpm.doancnpm.service;

import com.codecnpm.doancnpm.dto.LecturerDto;

import java.util.List;

public interface LecturerService {
    List<LecturerDto> getAllLecturers();
    LecturerDto getLecturerById(Long id);
    LecturerDto createLecturer(LecturerDto lecturerDto);
    LecturerDto updateLecturer(Long id, LecturerDto lecturerDto);
    void deleteLecturer(Long id);
}