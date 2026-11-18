package org.example.spring_boot_layered_todo.controller;

import jakarta.validation.Valid;
import org.example.spring_boot_layered_todo.DTO.TaskRequest;
import org.example.spring_boot_layered_todo.DTO.TaskResponse;
import org.example.spring_boot_layered_todo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //Create task
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {

        TaskResponse response = taskService.createTask(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    //Update task
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTaskDetails(
            @PathVariable Long id, @Valid @RequestBody TaskRequest request) {

        return ResponseEntity.ok(taskService.updateTaskDetails(id, request));
    }

    //Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    //Read by task name
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTaskByName(@RequestParam String taskName) {

        return ResponseEntity.ok(taskService.getTaskByName(taskName));
    }
}
