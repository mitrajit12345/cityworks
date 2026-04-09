package com.cityworks.citizen_service.dto;

import lombok.Data;

@Data
public class CitizenDTO {
    private Long citizenId;
    private String name;
    private String contactInfo;
    private String location;
    private String status;
}
