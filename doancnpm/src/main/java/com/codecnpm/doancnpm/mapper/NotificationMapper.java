package com.codecnpm.doancnpm.mapper;

import com.codecnpm.doancnpm.dto.NotificationDto;
import com.codecnpm.doancnpm.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    NotificationDto toDto(Notification notification);
    Notification toEntity(NotificationDto notificationDto);
}