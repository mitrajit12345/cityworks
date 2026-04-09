package com.cityworks.mapper;

import com.cityworks.dto.TaskResponseDto;
import com.cityworks.model.Task;

import java.util.List;

public class ResponseTaskMapper {
    private ResponseTaskMapper() {

    }
    public static TaskResponseDto toResponseDto(Task task) {
        if (task == null){
            return null;
        }
        return TaskResponseDto.builder()
                .taskId(task.getTaskId())
                .description(task.getDescription())
                .assignedTo(task.getAssignedTo())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .workOrderId(task.getWorkOrder() != null ? task.getWorkOrder().getOrderID() : null)
                .build();
    }

    public static List<TaskResponseDto> toResponseDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(ResponseTaskMapper::toResponseDto)
                .toList();
    }
}
