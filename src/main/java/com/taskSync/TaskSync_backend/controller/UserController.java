package com.taskSync.TaskSync_backend.controller;

import com.taskSync.TaskSync_backend.dto.AuthentificationDto;
import com.taskSync.TaskSync_backend.entity.User;
import com.taskSync.TaskSync_backend.security.JwtService;
import com.taskSync.TaskSync_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "user")
public class UserController {
    private UserService userService;
    private AuthenticationManager authentificationManager;
    private JwtService jwtService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authentificationManager = authenticationManager;
        this.jwtService = jwtService;
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

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public User searchUser(@PathVariable int id){
        return this.userService.searchUser(id);
    }

    @PostMapping(path = "connection")
    public ResponseEntity<?> connection(@RequestBody AuthentificationDto authentificationDto) {
        try {
            Authentication authenticate = authentificationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authentificationDto.username(), authentificationDto.password())
            );
            if (authenticate.isAuthenticated()) {
                Map<String, String> tokenResponse = this.jwtService.generate(authentificationDto.username());
                return ResponseEntity.ok(tokenResponse);
            }
            //Recuperer le tokenResponse sous forme de String

            return ResponseEntity.ok("Login ok");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non autorisé : " + ex.getMessage());
        }
    }

}
