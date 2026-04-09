package com.cityworks.citizen_service.controller;

import com.cityworks.citizen_service.api.APIResponse;
import com.cityworks.citizen_service.dto.ServiceRequestDTO;
import com.cityworks.citizen_service.service.ServiceRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService service;

    @PostMapping
    public ResponseEntity<APIResponse<ServiceRequestDTO>> create(@Valid @RequestBody ServiceRequestDTO dto) {

        APIResponse<ServiceRequestDTO> response = APIResponse.<ServiceRequestDTO>builder()
                .status("SUCCESS")
                .message("Request Created")
                .data(service.createRequest(dto))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<ServiceRequestDTO>>> getAll() {

        APIResponse<List<ServiceRequestDTO>> resonse = APIResponse.<List<ServiceRequestDTO>>builder()
                .status("SUCCESS")
                .message("All Requests")
                .data(service.getAllRequests())
                .build();

        return ResponseEntity.ok(resonse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ServiceRequestDTO>> getById(@PathVariable Long id) {
        APIResponse<ServiceRequestDTO> response = APIResponse.<ServiceRequestDTO>builder()
                .status("SUCCESS")
                .message("Request Found")
                .data(service.getById(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<APIResponse<ServiceRequestDTO>> approve(@PathVariable Long id) {
        APIResponse<ServiceRequestDTO> response = APIResponse.<ServiceRequestDTO>builder()
                .status("SUCCESS")
                .message("Approved")
                .data(service.approveRequest(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<APIResponse<ServiceRequestDTO>> reject(@PathVariable Long id) {
        APIResponse<ServiceRequestDTO> response = APIResponse.<ServiceRequestDTO>builder()
                .status("SUCCESS")
                .message("Rejected")
                .data(service.rejectRequest(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable Long id) {
        service.delete(id);

        APIResponse<String> response = APIResponse.<String>builder()
                .status("SUCCESS")
                .message("Deleted Successfully")
                .data(null)
                .build();

        return ResponseEntity.ok(response);
    }
}
