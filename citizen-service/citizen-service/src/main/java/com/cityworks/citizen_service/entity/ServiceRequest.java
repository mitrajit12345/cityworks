package com.cityworks.citizen_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    private Long citizenId;
    private Long assetId;

    private String description;
    private LocalDateTime submittedAt;
    private String status;
}
