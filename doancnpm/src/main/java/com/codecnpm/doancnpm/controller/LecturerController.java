package com.codecnpm.doancnpm.controller;

import com.codecnpm.doancnpm.dto.LecturerDto;
import com.codecnpm.doancnpm.service.LecturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecturers")
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping
    public ResponseEntity<List<LecturerDto>> getAllLecturers() {
        List<LecturerDto> lecturers = lecturerService.getAllLecturers();
        return new ResponseEntity<>(lecturers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LecturerDto> getLecturerById(@PathVariable("id") Long id) {
        LecturerDto lecturer = lecturerService.getLecturerById(id);
        return new ResponseEntity<>(lecturer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LecturerDto> createLecturer(@RequestBody LecturerDto lecturerDto) {
        LecturerDto createdLecturer = lecturerService.createLecturer(lecturerDto);
        return new ResponseEntity<>(createdLecturer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LecturerDto> updateLecturer(@PathVariable("id") Long id, @RequestBody LecturerDto lecturerDto) {
        LecturerDto updatedLecturer = lecturerService.updateLecturer(id, lecturerDto);
        return new ResponseEntity<>(updatedLecturer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecturer(@PathVariable("id") Long id) {
        lecturerService.deleteLecturer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}