package com.codecnpm.doancnpm.mapper;

import com.codecnpm.doancnpm.dto.GroupDto;
import com.codecnpm.doancnpm.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDto toDto(Group group);
    Group toEntity(GroupDto groupDto);
}