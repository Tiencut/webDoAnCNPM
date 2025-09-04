package com.codecnpm.doancnpm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codecnpm.doancnpm.dto.NotificationDto;
import com.codecnpm.doancnpm.entity.Notification;
import com.codecnpm.doancnpm.mapper.NotificationMapper;
import com.codecnpm.doancnpm.repository.NotificationRepository;
import com.codecnpm.doancnpm.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        return notificationMapper.toDto(notification);
    }

    @Override
    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification notification = notificationMapper.toEntity(notificationDto);
        return notificationMapper.toDto(notificationRepository.save(notification));
    }

    @Override
    public NotificationDto updateNotification(Long id, NotificationDto notificationDto) {
        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        existingNotification.setTitle(notificationDto.getTitle());
        existingNotification.setContent(notificationDto.getContent());
        existingNotification.setIsRead(notificationDto.isRead());
        // Update user if needed
        return notificationMapper.toDto(notificationRepository.save(existingNotification));
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}