package com.cityworks.dto;

import com.cityworks.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {

    private Long taskId;
    private Long workOrderId;
    private String description;
    private Long assignedTo;
    private LocalDate dueDate;
    private TaskStatus status;
}
