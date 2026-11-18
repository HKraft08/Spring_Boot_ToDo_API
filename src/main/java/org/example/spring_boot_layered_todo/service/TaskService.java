package org.example.spring_boot_layered_todo.service;

import org.example.spring_boot_layered_todo.DTO.TaskRequest;
import org.example.spring_boot_layered_todo.DTO.TaskResponse;
import org.example.spring_boot_layered_todo.entity.Tasks;
import org.example.spring_boot_layered_todo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Create task
    @Transactional
    public TaskResponse createTask(TaskRequest request) {

        Tasks task = new Tasks();
        task.setTaskName(request.taskName());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());

        Tasks savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    private TaskResponse mapToResponse(Tasks task) {

        return new TaskResponse(
                task.getId(),
                task.getTaskName(),
                task.getDescription(),
                task.getDueDate(),
                task.isCompleted()
        );
    }
}
