package com.cityworks.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskid")
    private Long taskID;

    @Column(name = "workorderid")
    private Long workOrderID;

    private String description;

    @Column(name = "assignedto")
    private Long assignedTo;

    @Column(name = "duedate")
    private LocalDateTime dueDate;

    private String status;

    public Long getTaskID() { return taskID; }
    public void setTaskID(Long taskID) { this.taskID = taskID; }

    public Long getWorkOrderID() { return workOrderID; }
    public void setWorkOrderID(Long workOrderID) { this.workOrderID = workOrderID; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getAssignedTo() { return assignedTo; }
    public void setAssignedTo(Long assignedTo) { this.assignedTo = assignedTo; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}