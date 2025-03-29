package com.example.service;

import com.example.dto.user.UserDto;
import com.example.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService extends BaseService<UserDto, Long> {
    Optional<UserDto> findByEmail(String email);
    boolean existsByEmail(String email);
    Page<UserDto> findAll(String search, Pageable pageable);
} 