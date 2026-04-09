package com.cityworks.controller;

import com.cityworks.api.APIResponse;
import com.cityworks.dto.CreateTaskDto;

import com.cityworks.dto.TaskResponseDto;
import com.cityworks.dto.TaskUpdateDto;
import com.cityworks.mapper.ResponseTaskMapper;
import com.cityworks.model.Task;
import com.cityworks.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity <APIResponse<TaskResponseDto>> createTask(@RequestBody @Valid CreateTaskDto createTaskDto){
        Task task = taskService.createTask(createTaskDto);
        TaskResponseDto taskResponseDto = ResponseTaskMapper.toResponseDto(task);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.<TaskResponseDto>builder()
                        .status("Success")
                        .message("Task created successfully")
                        .data(taskResponseDto)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity <APIResponse<TaskResponseDto>> findTaskById(@PathVariable("id") Long id){
        TaskResponseDto taskResponseDto = taskService.findTaskById(id);
        return ResponseEntity.ok(APIResponse.<TaskResponseDto>builder()
                .status("Success")
                .message("Task retrieved successfully")
                .data(taskResponseDto)

                .build());
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<TaskResponseDto>>> getAllTasks() {
        List<TaskResponseDto> tasksDto = taskService.findAllTasks();

        return ResponseEntity.ok(
                APIResponse.<List<TaskResponseDto>>builder()
                        .status("Success")
                        .message("Task retrieved successfully")
                        .data(tasksDto)
                        .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity <APIResponse<TaskResponseDto>> updateTaskById(@PathVariable("id") Long id, @RequestBody @Valid TaskUpdateDto taskUpdateDto){
        Task updatedTask = taskService.updateTaskById(id, taskUpdateDto);
        TaskResponseDto taskResponseDto = ResponseTaskMapper.toResponseDto(updatedTask);
        return  ResponseEntity.ok(APIResponse.<TaskResponseDto>builder()
                .status("Success")
                .message("Task updated successfully")
                .data(taskResponseDto)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);

        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .status("Success")
                        .message("Task deleted successfully")
                        .data(null)
                        .build()
        );
    }
}
