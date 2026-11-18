package org.example.spring_boot_layered_todo.service;

import org.example.spring_boot_layered_todo.DTO.TaskRequest;
import org.example.spring_boot_layered_todo.DTO.TaskResponse;
import org.example.spring_boot_layered_todo.entity.Tasks;
import org.example.spring_boot_layered_todo.exceptions.ResourceNotFoundException;
import org.example.spring_boot_layered_todo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        task.setCompleted(false);

        Tasks savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    //Update task
    @Transactional
    public TaskResponse updateTaskDetails(Long id, TaskRequest request) {

        Tasks task = findByIdOrThrow(id);

        task.setTaskName(request.taskName());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());

        if (request.isCompleted() != null) {
            task.setCompleted(request.isCompleted());
        }

        Tasks updatedTask =  taskRepository.save(task);

        return mapToResponse(updatedTask);
    }

    //Delete task
    @Transactional
    public void deleteTask(Long id) {

        Tasks task = findByIdOrThrow(id);

        taskRepository.delete(task);
    }

    //Read by task name
    @Transactional(readOnly = true)
    public List<TaskResponse> getTaskByName(String taskName) {

        List<Tasks> task = taskRepository.findByTaskName(taskName);

        if (task.isEmpty()) {
            throw new ResourceNotFoundException("Task not found with name: " + taskName);
        }

        return task.stream()
                .map(this::mapToResponse)
                .toList();
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

    private Tasks findByIdOrThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
    }
}
