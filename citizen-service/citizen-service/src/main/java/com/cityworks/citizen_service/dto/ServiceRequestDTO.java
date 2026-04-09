package com.cityworks.citizen_service.dto;
import jakarta.validation.constraints.*;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ServiceRequestDTO {

    private Long requestId;

    @NotNull(message = "Citizen ID is required")
    private Long citizenId;

    @NotNull(message = "Asset ID is required")
    private Long assetId;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    private LocalDateTime submittedAt;
    private String status;
}
