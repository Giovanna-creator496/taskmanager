package it.giovanna.taskmanager.dto.task;

import jakarta.validation.constraints.Size;

import it.giovanna.taskmanager.model.TaskStatus;

public record TaskUpdateRequest (
        @Size(max=120) String title,
        @Size(max=1000) String description,
        TaskStatus status
){}
