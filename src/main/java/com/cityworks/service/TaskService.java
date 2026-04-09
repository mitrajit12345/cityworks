package com.cityworks.service;

import com.cityworks.dto.CreateTaskDto;
import com.cityworks.dto.TaskResponseDto;
import com.cityworks.dto.TaskUpdateDto;
import com.cityworks.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(CreateTaskDto t);
    TaskResponseDto findTaskById(Long id);
    Task findTaskEntityById(Long id);
    List<TaskResponseDto> findAllTasks();
    Task updateTaskById(Long id, TaskUpdateDto dto);
    void deleteTask(Long id);
}
