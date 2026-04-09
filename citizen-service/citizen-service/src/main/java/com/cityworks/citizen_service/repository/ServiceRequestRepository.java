package com.cityworks.citizen_service.repository;

import com.cityworks.citizen_service.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
}
