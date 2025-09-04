package com.codecnpm.doancnpm.service.impl;

import com.codecnpm.doancnpm.dto.StudentDto;
import com.codecnpm.doancnpm.entity.Student;
import com.codecnpm.doancnpm.mapper.StudentMapper;
import com.codecnpm.doancnpm.repository.StudentRepository;
import com.codecnpm.doancnpm.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setMajor(studentDto.getMajor());
        existingStudent.setAcademicYear(studentDto.getAcademicYear());
        // Update user and courseSection if needed
        // existingStudent.setUser(studentMapper.toEntity(studentDto.getUser()));
        // existingStudent.setCourseSection(studentMapper.toEntity(studentDto.getCourseSection()));
        return studentMapper.toDto(studentRepository.save(existingStudent));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}