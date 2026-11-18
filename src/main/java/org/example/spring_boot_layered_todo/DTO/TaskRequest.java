package org.example.spring_boot_layered_todo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskRequest(
        @NotBlank(message = "Task name is required")
        @Size(max = 100, message = "Task name must not exceed 100 characters")
        String taskName,

        @NotBlank(message = "Task description is required")
        @Size(max = 255, message = "Task description must not exceed 255 characters")
        String description,

        @NotNull(message = "Due date is required")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dueDate
) {

}
