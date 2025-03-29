package com.example.repository.base;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BaseQueryRepository<E, ID> extends JpaRepository<E, ID>, QuerydslPredicateExecutor<E> {
    Page<E> findAll(Predicate predicate, Pageable pageable);
} 