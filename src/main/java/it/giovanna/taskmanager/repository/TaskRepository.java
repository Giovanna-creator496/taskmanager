package it.giovanna.taskmanager.repository;

import it.giovanna.taskmanager.model.Task;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task>
    findByOwner_IdAndDeletedAtIsNull(Long ownerId);

    Optional<Task>
    findByIdAndOwner_IdAndDeletedAtIsNull(Long id,Long ownerId);
}
