package com.taskSync.TaskSync_backend.service;

import com.taskSync.TaskSync_backend.entity.Task;
import com.taskSync.TaskSync_backend.entity.User;
import com.taskSync.TaskSync_backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Task> getListTask() {
        return this.taskRepository.findAll();
    }

    public void modifyTask(int id, Task task) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isPresent()){
            Task modifyTask = optionalTask.get();
            modifyTask.setTitle(task.getTitle());
            modifyTask.setDescription(task.getDescription());
            modifyTask.setDueDate(task.getDueDate());
            modifyTask.setTags(task.getTags());
            modifyTask.setStatus(task.getStatus());
            this.taskRepository.save(modifyTask);
        }
        else {
            throw new RuntimeException("Client with ID " + id + " not found.");
        }
    }

    public void deleteTask(int id) {
        this.taskRepository.deleteById(id);
    }
}
