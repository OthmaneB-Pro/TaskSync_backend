package com.taskSync.TaskSync_backend.service;

import com.taskSync.TaskSync_backend.entity.Task;
import com.taskSync.TaskSync_backend.entity.User;
import com.taskSync.TaskSync_backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }
    public void createTask(Task task){
        User user = this.userService.getUser(task.getUser());
        task.setUser(user);
        this.taskRepository.save(task);
    }
}
