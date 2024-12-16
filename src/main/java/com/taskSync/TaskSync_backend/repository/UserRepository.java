package com.taskSync.TaskSync_backend.repository;

import com.taskSync.TaskSync_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
