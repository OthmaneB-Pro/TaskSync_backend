package com.taskSync.TaskSync_backend.controller;

import com.taskSync.TaskSync_backend.entity.Task;
import com.taskSync.TaskSync_backend.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Task> getListTask(){
        return this.taskService.getListTask();
    }

    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void modifyTask(@PathVariable int id, @RequestBody Task task){
        this.taskService.modifyTask(id, task);
    }

    @DeleteMapping(path = "{id}")
    public void deleteTask(@PathVariable int id){
        this.taskService.deleteTask(id);
    }

}
