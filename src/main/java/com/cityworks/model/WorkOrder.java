package com.cityworks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "workorder")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

}