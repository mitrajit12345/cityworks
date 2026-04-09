package com.cityworks.citizen_service.service;

import com.cityworks.citizen_service.dto.ServiceRequestDTO;
import java.util.List;

public interface ServiceRequestService {
    ServiceRequestDTO createRequest(ServiceRequestDTO dto);
    List<ServiceRequestDTO> getAllRequests();

    ServiceRequestDTO approveRequest(Long id);

    ServiceRequestDTO rejectRequest(Long id);

    void delete(Long id);

    ServiceRequestDTO getById(Long id);
}
