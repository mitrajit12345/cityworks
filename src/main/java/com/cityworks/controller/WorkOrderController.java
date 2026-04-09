package com.cityworks.controller;

import com.cityworks.mapper.WorkOrderMapper;
import com.cityworks.model.WorkOrder;
import com.cityworks.requests.AssignWorkerRequest;
import com.cityworks.requests.CreateWorkOrderRequest;
import com.cityworks.response.ApiResponse;
import com.cityworks.response.WorkOrderResponse;
import com.cityworks.service.WorkOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/workorders")
public class WorkOrderController {

    private static final Logger logger =
            LoggerFactory.getLogger(WorkOrderController.class);

    private final WorkOrderService service;

    public WorkOrderController(WorkOrderService service) {
        this.service = service;
    }

    // ✅ CREATE WORK ORDER
    @PostMapping
    public ApiResponse<WorkOrderResponse> createWorkOrder(
            @RequestBody CreateWorkOrderRequest request) {

        logger.info("Received request to create WorkOrder");

        WorkOrder wo = service.createWorkOrder(request);

        return new ApiResponse<>(
                "Work order created successfully",
                201,
                WorkOrderMapper.toResponse(wo)
        );
    }

    // ✅ ASSIGN WORKER
    @PutMapping("/assign")
    public ApiResponse<WorkOrderResponse> assignWorker(
            @RequestBody AssignWorkerRequest request) {

        logger.info("Received request to assign worker");

        WorkOrder wo = service.assignWorker(request);

        return new ApiResponse<>(
                "Worker assigned successfully",
                200,
                WorkOrderMapper.toResponse(wo)
        );
    }

    // ✅ VIEW USER WORK ORDERS
    @GetMapping
    public ApiResponse<List<WorkOrderResponse>> viewWorkOrders(
            @RequestParam Long userId) {

        logger.info("Fetching workorders for user {}", userId);

        List<WorkOrderResponse> responseList =
                service.getWorkOrdersForUser(userId)
                        .stream()
                        .map(WorkOrderMapper::toResponse)
                        .collect(Collectors.toList());

        return new ApiResponse<>(
                "Work orders fetched successfully",
                200,
                responseList
        );
    }

    // ✅ UPDATE STATUS
    @PutMapping("/status")
    public ApiResponse<WorkOrderResponse> updateStatus(
            @RequestParam Long orderId,
            @RequestParam String status) {

        logger.info("Updating WorkOrder {} status", orderId);

        WorkOrder wo = service.updateStatus(orderId, status);

        return new ApiResponse<>(
                "Work order status updated",
                200,
                WorkOrderMapper.toResponse(wo)
        );
    }
}
