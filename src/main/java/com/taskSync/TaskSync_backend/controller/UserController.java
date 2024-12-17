package com.taskSync.TaskSync_backend.controller;

import com.taskSync.TaskSync_backend.entity.User;
import com.taskSync.TaskSync_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUser(){
       return this.userService.getAllUser();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody User user){
        this.userService.createUser(user);
    }
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void modifyUser(@PathVariable int id, @RequestBody User user){
        this.userService.modifyUser(id, user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable int id){
        this.userService.deleteUser(id);
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public User searchUser(@PathVariable int id){
        return this.userService.searchUser(id);
    }
}
