package it.giovanna.taskmanager.dto.task;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

public record TaskCreateRequest (
        @NotBlank @Size(max=120) String title,
        @Size(max=100) String description
){}
