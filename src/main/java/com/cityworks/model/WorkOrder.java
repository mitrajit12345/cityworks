package com.cityworks.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "workorder")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private Long orderID;

    @Column(name = "requestid")
    private Long requestID;

    @Column(name = "assetid")
    private Long assetID;

    @Column(name = "assignedto")
    private Long assignedTo;

    @Column(name = "assignedat")
    private LocalDateTime assignedAt;

    @Column(name = "duedate")
    private LocalDateTime dueDate;

    @Column(name = "status")
    private String status;

    public Long getOrderID() { return orderID; }
    public void setOrderID(Long orderID) { this.orderID = orderID; }

    public Long getRequestID() { return requestID; }
    public void setRequestID(Long requestID) { this.requestID = requestID; }

    public Long getAssetID() { return assetID; }
    public void setAssetID(Long assetID) { this.assetID = assetID; }

    public Long getAssignedTo() { return assignedTo; }
    public void setAssignedTo(Long assignedTo) { this.assignedTo = assignedTo; }

    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}