package com.cityworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cityworks.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskIdAndDeletedFalse(Long taskId);
    Optional<List<Task>> findAllByDeletedFalse();
}

