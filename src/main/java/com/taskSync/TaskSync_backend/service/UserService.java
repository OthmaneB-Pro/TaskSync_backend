package com.taskSync.TaskSync_backend.service;

import com.taskSync.TaskSync_backend.entity.User;
import com.taskSync.TaskSync_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUser(){
        return this.userRepository.findAll();
    }

    public void createUser(User user) {
        this.userRepository.save(user);
    }

    public void modifyUser(int id, User user) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()){
            User modifyUser = optionalUser.get();
            modifyUser.setUsername(user.getUsername());
            modifyUser.setDateOfBirth(user.getDateOfBirth());
            modifyUser.setPassword(user.getPassword());
            this.userRepository.save(modifyUser);
        }
        else {
            throw new RuntimeException("Client with ID " + id + " not found.");
        }
    }

    public void deleteUser(int id) {
        this.userRepository.deleteById(id);
    }

    public User searchUser(int id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        return optionalUser.orElse(null);
    }
}
