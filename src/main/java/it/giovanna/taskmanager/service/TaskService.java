package it.giovanna.taskmanager.service;

import it.giovanna.taskmanager.dto.task.*;
import it.giovanna.taskmanager.model.*;
import it.giovanna.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    private User currentUser(String username) {
        return userService.getByUsername(username);
    }

    private Task getOwnedTaskOrThrow(Long taskId, Long ownerId) {
        return taskRepository.findByIdAndOwner_IdAndDeletedAtIsNull(taskId, ownerId).orElseThrow(() -> new RuntimeException("Task not found"));

    }

    public TaskResponse create(String username, TaskCreateRequest req) {
        User owner = currentUser(username);
        Task task = Task.builder()
                .title(req.title())
                .description(req.description())
                .status(TaskStatus.TODO)
                .owner(owner)
                .build();
        Task saved = taskRepository.save(task);
        return toResponse(saved);
    }

    public List<TaskResponse> list(String username) {
        User owner = currentUser(username);
        return taskRepository.findByOwner_IdAndDeletedAtIsNull(owner.getId())
                .stream().map(this::toResponse).toList();
    }

    public TaskResponse getById(String username, Long id) {
        User owner = currentUser(username);
        return toResponse(getOwnedTaskOrThrow(id, owner.getId()));
    }

    public TaskResponse update(String username, Long id, TaskUpdateRequest req) {
        User owner = currentUser(username);
        Task task = getOwnedTaskOrThrow(id, owner.getId());
        if (task.getStatus() == TaskStatus.DONE) {
            throw new RuntimeException("Task is done and cannot be modified");
        }
        if (req.title() != null) task.setTitle(req.title());
        if (req.description() != null) task.setDescription(req.description());
        if (req.status() != null) {
            if (req.status() == TaskStatus.DONE) {
                task.setStatus(TaskStatus.DONE);
                task.setCompletedAt(LocalDateTime.now());
            } else {
                task.setStatus(req.status());
            }
        }
        Task saved = taskRepository.save(task);
        return toResponse(saved);
    }
    public void softDelete(String username, Long id) {
        User owner = currentUser(username);
        Task task = getOwnedTaskOrThrow(id, owner.getId());
        task.setDeletedAt(LocalDateTime.now());
        taskRepository.save(task);
    }
    private TaskResponse toResponse(Task t) {
        return new TaskResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getStatus(),
                t.getCreatedAt(),
                t.getUpdatedAt(),
                t.getCompletedAt()
        );
    }
}