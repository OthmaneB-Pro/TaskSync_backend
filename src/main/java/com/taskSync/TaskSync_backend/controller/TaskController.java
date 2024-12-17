package com.taskSync.TaskSync_backend.controller;

import com.taskSync.TaskSync_backend.entity.Task;
import com.taskSync.TaskSync_backend.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody Task task){
        this.taskService.createTask(task);
    }
}
