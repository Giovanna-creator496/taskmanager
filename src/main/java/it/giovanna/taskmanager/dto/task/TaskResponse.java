package it.giovanna.taskmanager.dto.task;

import it.giovanna.taskmanager.model.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse (
        Long id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime completedAt
){}
