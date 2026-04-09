package com.cityworks.mapper;

import com.cityworks.dto.CreateTaskDto;
import com.cityworks.enums.TaskStatus;
import com.cityworks.model.Task;
import com.cityworks.model.WorkOrder;

public class CreateTaskMapper {
    private CreateTaskMapper(){

    }

    public static Task toEntity(CreateTaskDto dto){
        if(dto == null) {
            return null;
        }
        WorkOrder wo = new WorkOrder();
        wo.setOrderID(dto.getWorkOrderId());

        return Task.builder()
                .workOrder(wo)
                .description(dto.getDescription())
                .assignedTo(dto.getAssignedTo())
                .dueDate(dto.getDueDate())
                .status(TaskStatus.OPEN)
                .build();
    }

    public static CreateTaskDto toCreateTaskDto(Task task) {
        if (task == null) {
            return null;
        }

        return new CreateTaskDto(
                task.getWorkOrder() != null ? task.getWorkOrder().getOrderID() : null,
                task.getDescription(),
                task.getAssignedTo(),
                task.getDueDate()
        );
    }
}
