package com.cityworks.repository;
import java.util.*;
import com.cityworks.model.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
	
	List<WorkOrder> findByAssignedTo(Long assignedTo);

}