package com.codecnpm.doancnpm.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto {
    private Long notificationId;
    // private UserDto user;
    private String title;
    private String content;
    private boolean isRead;
    private LocalDateTime createdAt;
}