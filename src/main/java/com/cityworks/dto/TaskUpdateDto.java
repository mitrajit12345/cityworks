package com.cityworks.dto;

import com.cityworks.enums.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskUpdateDto {
    private Long workOrderId;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    private Long assignedTo;

    @FutureOrPresent(message = "Due date must be today or in the future")
    private LocalDate dueDate;

    private TaskStatus status;
}
