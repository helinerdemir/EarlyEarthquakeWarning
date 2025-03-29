package com.example.service.impl;

import com.example.dto.user.UserDto;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.service.base.BaseServiceImpl;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User, UserDto, Long, UserRepository, UserMapper> implements UserService {

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDto);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Page<UserDto> findAll(String search, Pageable pageable) {
        Predicate predicate = createSearchPredicate(search);
        return repository.findAll(predicate, pageable).map(mapper::toDto);
    }

    private Predicate createSearchPredicate(String search) {
        if (search == null || search.trim().isEmpty()) {
            return null;
        }

        StringPath firstName = QUser.user.firstName;
        StringPath lastName = QUser.user.lastName;
        StringPath email = QUser.user.email;

        return firstName.containsIgnoreCase(search)
                .or(lastName.containsIgnoreCase(search))
                .or(email.containsIgnoreCase(search));
    }
} 