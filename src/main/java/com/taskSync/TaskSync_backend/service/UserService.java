package com.taskSync.TaskSync_backend.service;

import com.taskSync.TaskSync_backend.entity.User;
import com.taskSync.TaskSync_backend.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUser(){
        return this.userRepository.findAll();
    }
    public User getUser(User user){
        return this.userRepository.findByUsername(user.getUsername());
    }

    public void createUser(User user) {
        String passwordCrypt = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordCrypt);
        this.userRepository.save(user);
    }

    public void modifyUser(int id, User user) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()){
            User modifyUser = optionalUser.get();
            modifyUser.setUsername(user.getUsername());
            modifyUser.setDateOfBirth(user.getDateOfBirth());
            modifyUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  this.userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Aucun utilisateur trouvé");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}
