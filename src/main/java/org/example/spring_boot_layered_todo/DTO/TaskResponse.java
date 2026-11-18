package org.example.spring_boot_layered_todo.DTO;

import java.time.LocalDate;

public record TaskResponse(
        Long id,
        String taskName,
        String description,
        LocalDate dueDate,
        boolean isCompleted
) {
}
