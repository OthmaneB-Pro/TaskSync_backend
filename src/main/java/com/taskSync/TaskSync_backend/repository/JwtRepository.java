package com.taskSync.TaskSync_backend.repository;

import com.taskSync.TaskSync_backend.entity.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRepository extends JpaRepository<Jwt, Integer> {
}