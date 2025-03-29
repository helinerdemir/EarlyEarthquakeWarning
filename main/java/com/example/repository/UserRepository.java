package com.example.repository;

import com.example.entity.User;
import com.example.repository.base.BaseQueryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseQueryRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
} 