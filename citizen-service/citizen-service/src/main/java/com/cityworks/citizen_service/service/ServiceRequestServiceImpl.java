package com.cityworks.citizen_service.service;

import com.cityworks.citizen_service.dto.ServiceRequestDTO;
import com.cityworks.citizen_service.entity.ServiceRequest;
import com.cityworks.citizen_service.exception.ResourceNotFoundException;
import com.cityworks.citizen_service.mapper.ServiceRequestMapper;
import com.cityworks.citizen_service.repository.ServiceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private final ServiceRequestRepository repository;
    private final EntityManagerFactoryInfo entityManagerFactoryInfo;
    private final ServiceRequestMapper mapper;
    private final HandlerMapping resourceHandlerMapping;

    private ServiceRequestDTO mapToDTO(ServiceRequest entity){
        ServiceRequestDTO dto = new ServiceRequestDTO();

        dto.setRequestId(entity.getRequestId());
        dto.setCitizenId(entity.getCitizenId());
        dto.setAssetId(entity.getAssetId());
        dto.setDescription(entity.getDescription());
        dto.setSubmittedAt(entity.getSubmittedAt());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    @Override
    public ServiceRequestDTO createRequest(ServiceRequestDTO dto) {

        ServiceRequest entity = mapper.toEntity(dto);

        entity.setStatus("PENDING");
        entity.setSubmittedAt(LocalDateTime.now());

        ServiceRequest saved = repository.save(entity);

        return mapper.toDTO(saved);
    }

    @Override
    public List<ServiceRequestDTO> getAllRequests() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public ServiceRequestDTO approveRequest(Long id) {
        ServiceRequest entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));

        entity.setStatus("APPROVED");

        ServiceRequest updated = repository.save(entity);

        return mapper.toDTO(updated);
    }

    @Override
    public ServiceRequestDTO rejectRequest(Long id) {
        ServiceRequest entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));

        entity.setStatus("REJECTED");

        ServiceRequest updated = repository.save(entity);

        return mapToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        ServiceRequest entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));

        repository.delete(entity);
    }

    @Override
    public ServiceRequestDTO getById(Long id) {
        ServiceRequest entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));

        return mapper.toDTO(entity);
    }
}
