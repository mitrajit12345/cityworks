package com.cityworks.service;

import com.cityworks.model.User;
import com.cityworks.model.WorkOrder;
import com.cityworks.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorkerService {

    private final UserRepository userRepo;

    public WorkerService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    
    public List<User> getAvailableWorkers() {
        return userRepo.findByRoleAndStatus("Worker", "Active");
    }
}