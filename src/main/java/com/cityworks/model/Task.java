package com.cityworks.model;

import com.cityworks.audit.Audit;
import com.cityworks.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name="task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Builder
public class Task extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @ManyToOne
    @JoinColumn(name = "workOrderId")
    @JsonIgnoreProperties({"requestID", "assetID", "assignedTo", "assignedAt", "dueDate", "status"})
    private WorkOrder workOrder;

    private String description;

    private Long assignedTo;

    private LocalDate dueDate;

    @Column(nullable = false)
    private boolean deleted = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TaskStatus status = TaskStatus.OPEN;
}
