package com.cityworks.mapper;

import com.cityworks.model.WorkOrder;
import com.cityworks.response.WorkOrderResponse;

public class WorkOrderMapper {

    public static WorkOrderResponse toResponse(WorkOrder wo) {

        WorkOrderResponse response = new WorkOrderResponse();
        response.setOrderId(wo.getOrderID());
        response.setRequestId(wo.getRequestID());
        response.setAssetId(wo.getAssetID());
        response.setAssignedTo(wo.getAssignedTo());
        response.setAssignedAt(wo.getAssignedAt());
        response.setDueDate(wo.getDueDate());
        response.setStatus(wo.getStatus());

        return response;
    }
}
