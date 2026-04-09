package com.cityworks.citizen_service.repository;

import com.cityworks.citizen_service.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
}

