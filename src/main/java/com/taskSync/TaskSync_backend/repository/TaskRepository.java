package com.taskSync.TaskSync_backend.repository;

import com.taskSync.TaskSync_backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
