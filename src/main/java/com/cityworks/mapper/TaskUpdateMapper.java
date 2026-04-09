package com.cityworks.mapper;

import com.cityworks.dto.TaskUpdateDto;
import com.cityworks.model.Task;
import com.cityworks.model.WorkOrder;

public class TaskUpdateMapper {
    private TaskUpdateMapper(){
        //utility class
    }

    public static void toEntity(Task task, TaskUpdateDto dto){
        if (dto == null || task == null){
           return;
        }

        //Update Work Order(F.K.)
        if(dto.getWorkOrderId() != null){
            WorkOrder wo = new WorkOrder();
            wo.setOrderID(dto.getWorkOrderId());
            task.setWorkOrder(wo);
        }

        //Update Description
        if(dto.getDescription() != null){
            task.setDescription(dto.getDescription());
        }

        //Update Assigned To
        if(dto.getAssignedTo() != null){
            task.setAssignedTo(dto.getAssignedTo());
        }

        //Update Due Date
        if(dto.getDueDate() != null){
            task.setDueDate(dto.getDueDate());
        }

        //Update Status
        if(dto.getStatus() != null) {
            task.setStatus(dto.getStatus());
        }
    }
}
