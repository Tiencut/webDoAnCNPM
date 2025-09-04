package com.codecnpm.doancnpm.service;

import com.codecnpm.doancnpm.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAllNotifications();
    NotificationDto getNotificationById(Long id);
    NotificationDto createNotification(NotificationDto notificationDto);
    NotificationDto updateNotification(Long id, NotificationDto notificationDto);
    void deleteNotification(Long id);
}