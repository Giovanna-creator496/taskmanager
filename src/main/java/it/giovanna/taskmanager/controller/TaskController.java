package it.giovanna.taskmanager.controller;

import it.giovanna.taskmanager.dto.task.*;
import it.giovanna.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final  TaskService taskService;
    private String username(Authentication auth){
        return auth.getName();
    }

    @PostMapping
    public  ResponseEntity<TaskResponse>create(Authentication auth,@Valid @RequestBody TaskCreateRequest req){
        return  ResponseEntity.ok(taskService.create(username(auth), req));
    }
    @GetMapping
    public  ResponseEntity<List<TaskResponse>>list(Authentication auth){
        return ResponseEntity.ok(taskService.list(username(auth)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(Authentication auth, @PathVariable Long id){
        return ResponseEntity.ok(taskService.getById(username(auth), id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse>update(Authentication auth,@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest req){
        return  ResponseEntity.ok(taskService.update(username(auth), id, req));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(Authentication auth, @PathVariable Long id){
        taskService.softDelete(username(auth), id);
        return ResponseEntity.noContent().build();
    }

}
