package com.codecnpm.doancnpm.mapper;

import com.codecnpm.doancnpm.dto.StudentDto;
import com.codecnpm.doancnpm.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto toDto(Student student);
    Student toEntity(StudentDto studentDto);
}