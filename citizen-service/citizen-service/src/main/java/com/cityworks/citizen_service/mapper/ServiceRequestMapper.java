package com.cityworks.citizen_service.mapper;

import com.cityworks.citizen_service.dto.ServiceRequestDTO;
import com.cityworks.citizen_service.entity.ServiceRequest;
import org.springframework.stereotype.Component;

@Component
public class ServiceRequestMapper {
    public ServiceRequestDTO toDTO(ServiceRequest entity){
        ServiceRequestDTO dto = new ServiceRequestDTO();

        dto.setRequestId(entity.getRequestId());
        dto.setCitizenId(entity.getCitizenId());
        dto.setAssetId(entity.getAssetId());
        dto.setDescription(entity.getDescription());
        dto.setSubmittedAt(entity.getSubmittedAt());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    public ServiceRequest toEntity(ServiceRequestDTO dto){
        ServiceRequest entity = new ServiceRequest();

        entity.setCitizenId(dto.getCitizenId());
        entity.setAssetId(dto.getAssetId());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}
