package com.cityworks.service;

import com.cityworks.exception.InvalidOperationException;
import com.cityworks.exception.ResourceNotFoundException;
import com.cityworks.model.*;
import com.cityworks.repository.WorkOrderRepository;
import com.cityworks.repository.UserRepository;
import com.cityworks.requests.AssignWorkerRequest;
import com.cityworks.requests.CreateWorkOrderRequest;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkOrderService {

    private static final Logger logger =
            LoggerFactory.getLogger(WorkOrderService.class);

    private final WorkOrderRepository workOrderRepo;
    private final UserRepository userRepo;
    private final EntityManager entityManager;

    public WorkOrderService(
            WorkOrderRepository workOrderRepo,
            UserRepository userRepo,
            EntityManager entityManager) {

        this.workOrderRepo = workOrderRepo;
        this.userRepo = userRepo;
        this.entityManager = entityManager;
    }

    // ✅ CREATE WORKORDER
    public WorkOrder createWorkOrder(CreateWorkOrderRequest requestDto) {

        logger.info("Creating WorkOrder for RequestID={}, AssetID={}",
                requestDto.getRequestId(), requestDto.getAssetId());

        ServiceRequest request =
                entityManager.find(ServiceRequest.class, requestDto.getRequestId());

        if (request == null) {
            logger.error("ServiceRequest {} not found", requestDto.getRequestId());
            throw new ResourceNotFoundException("Service request not yet created");
        }

        if ("Resolved".equalsIgnoreCase(request.getStatus())) {
            logger.warn("ServiceRequest {} already resolved", requestDto.getRequestId());
            throw new InvalidOperationException("Service request already resolved");
        }

        Asset asset =
                entityManager.find(Asset.class, requestDto.getAssetId());

        if (asset == null) {
            logger.error("Asset {} does not exist", requestDto.getAssetId());
            throw new ResourceNotFoundException("Asset does not exist");
        }

        WorkOrder wo = new WorkOrder();
        wo.setRequestID(requestDto.getRequestId());
        wo.setAssetID(requestDto.getAssetId());
        wo.setStatus("NEW");

        WorkOrder saved = workOrderRepo.save(wo);

        logger.info("WorkOrder {} created successfully", saved.getOrderID());
        return saved;
    }

    // ✅ ASSIGN WORKER
    public WorkOrder assignWorker(AssignWorkerRequest requestDto) {

        logger.info("Assigning Worker {} to WorkOrder {}",
                requestDto.getWorkerId(), requestDto.getOrderId());

        WorkOrder wo = workOrderRepo.findById(requestDto.getOrderId())
                .orElseThrow(() -> {
                    logger.error("WorkOrder {} not found", requestDto.getOrderId());
                    return new ResourceNotFoundException("Work order does not exist");
                });

        if ("COMPLETED".equalsIgnoreCase(wo.getStatus())) {
            logger.warn("WorkOrder {} already completed", requestDto.getOrderId());
            throw new InvalidOperationException("Work order already completed");
        }

        User worker = userRepo.findById(requestDto.getWorkerId())
                .orElseThrow(() -> {
                    logger.error("Worker {} does not exist", requestDto.getWorkerId());
                    return new ResourceNotFoundException("Worker does not exist");
                });

        if (!"Active".equalsIgnoreCase(worker.getStatus())) {
            logger.warn("Worker {} is inactive", requestDto.getWorkerId());
            throw new InvalidOperationException("Worker is inactive");
        }

        wo.setAssignedTo(requestDto.getWorkerId());
        wo.setAssignedAt(LocalDateTime.now());
        wo.setStatus("ASSIGNED");

        WorkOrder updated = workOrderRepo.save(wo);

        logger.info("Worker {} assigned to WorkOrder {}",
                requestDto.getWorkerId(), requestDto.getOrderId());

        return updated;
    }

    // ✅ VIEW USER WORKORDERS
    public List<WorkOrder> getWorkOrdersForUser(Long userId) {
        logger.debug("Fetching WorkOrders for User {}", userId);
        return workOrderRepo.findByAssignedTo(userId);
    }

    // ✅ UPDATE STATUS
    public WorkOrder updateStatus(Long orderId, String status) {

        logger.info("Updating WorkOrder {} status to {}", orderId, status);

        WorkOrder wo = workOrderRepo.findById(orderId)
                .orElseThrow(() -> {
                    logger.error("WorkOrder {} not found for status update", orderId);
                    return new ResourceNotFoundException("Work order does not exist");
                });

        wo.setStatus(status);
        WorkOrder updated = workOrderRepo.save(wo);

        logger.info("WorkOrder {} status updated to {}", orderId, status);
        return updated;
    }
}