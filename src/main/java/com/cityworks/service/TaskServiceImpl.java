package com.cityworks.service;

import com.cityworks.dto.TaskResponseDto;
import com.cityworks.dto.TaskUpdateDto;
import com.cityworks.mapper.CreateTaskMapper;
import com.cityworks.mapper.ResponseTaskMapper;
import com.cityworks.mapper.TaskUpdateMapper;
import com.cityworks.repository.TaskRepository;
import com.cityworks.dto.CreateTaskDto;
import com.cityworks.exception.TaskNotFoundException;
import com.cityworks.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskRepository taskRepository;
    @Override
    public Task createTask(CreateTaskDto createTaskDto) {
        Task task = CreateTaskMapper.toEntity(createTaskDto);
        return taskRepository.save(task);
    }

    @Override
    public TaskResponseDto findTaskById(Long id){
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
        return ResponseTaskMapper.toResponseDto(task);
    }

    @Override
    public Task findTaskEntityById(Long id) {
        return taskRepository.findByTaskIdAndDeletedFalse(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
    }

    @Override
    public List<TaskResponseDto> findAllTasks() {
        List<Task> tasks = taskRepository.findAllByDeletedFalse().orElseThrow(() -> new TaskNotFoundException("No tasks found"));
        return ResponseTaskMapper.toResponseDtoList(tasks);
    }

    @Override
    public Task updateTaskById(Long id, TaskUpdateDto dto){
        Task existingTask = findTaskEntityById(id);
        TaskUpdateMapper.toEntity(existingTask, dto);
        return taskRepository.save(existingTask);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        Task task = findTaskEntityById(id);
        if(task.isDeleted()){
            throw new TaskNotFoundException("Task with id " + id + " does not exist");
        }
        task.setDeleted(true);
        taskRepository.save(task);
    }
}
