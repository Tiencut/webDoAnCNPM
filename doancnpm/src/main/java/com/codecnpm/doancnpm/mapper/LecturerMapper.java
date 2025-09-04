package com.codecnpm.doancnpm.mapper;

import com.codecnpm.doancnpm.dto.LecturerDto;
import com.codecnpm.doancnpm.entity.Lecturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LecturerMapper {
    LecturerMapper INSTANCE = Mappers.getMapper(LecturerMapper.class);

    LecturerDto toDto(Lecturer lecturer);
    Lecturer toEntity(LecturerDto lecturerDto);
}