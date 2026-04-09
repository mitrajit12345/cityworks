package com.cityworks.controller;

import com.cityworks.model.User;
import com.cityworks.response.ApiResponse;
import com.cityworks.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    private static final Logger logger =
            LoggerFactory.getLogger(WorkerController.class);

    private final WorkerService service;

    public WorkerController(WorkerService service) {
        this.service = service;
    }

    @GetMapping("/available")
    public ApiResponse<List<User>> getAvailableWorkers() {
        logger.info("Fetching available workers");
        return new ApiResponse<>(
                "Available workers fetched",
                200,
                service.getAvailableWorkers()
        );
    }
}