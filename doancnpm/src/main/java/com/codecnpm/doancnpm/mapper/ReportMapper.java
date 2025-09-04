package com.codecnpm.doancnpm.mapper;

import com.codecnpm.doancnpm.dto.ReportDto;
import com.codecnpm.doancnpm.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    ReportDto toDto(Report report);
    Report toEntity(ReportDto reportDto);
}